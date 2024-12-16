package co.getaim.api.service

import co.getaim.api.dto.CreatePortfolioCommand
import co.getaim.api.dto.WithdrawalWalletCommand
import co.getaim.api.dto.event.RequestFailedEvent
import co.getaim.api.dto.event.RequestSucceededEvent
import co.getaim.exception.portfolio.PortfolioException
import co.getaim.exception.portfolio.PortfolioExceptionType.MONEY_EXCEED
import co.getaim.storage.persistence.mysql.persistence.PortfolioPersistService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
@Transactional
class PortfolioService(
    private val portfolioPersistService: PortfolioPersistService,
    private val walletService: WalletService,
    private val requestService: RequestService,
    private val securitiesService: SecuritiesService,
    private val investmentService: InvestmentService,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun save(userId: Long, command: CreatePortfolioCommand) {

        try {
            val wallet = walletService.find(userId)!!
            val request = requestService.find(command.requestId)
            val maximumMoney = request.maximumMoney(wallet.money.price)

            val securitiesId = command.securitiesList.map { it.securityId }
            val securities = securitiesService.findAllById(securitiesId)

            val commandMap = command.securitiesList.associateBy({ it.securityId }, { it.quantity })

            val totalPrice = securities
                .map { it.price.multiply(BigDecimal(commandMap[it.id]!!)) }
                .sumOf { it }

            if (totalPrice > maximumMoney) {
                throw PortfolioException(MONEY_EXCEED)
            }

            val portfolio = portfolioPersistService.save(userId, command.requestId)

            val ret = command.securitiesList
                .map { investmentService.save(portfolio.id, it.securityId, it.quantity) }
                .toList()

            walletService.decreaseMoney(userId, WithdrawalWalletCommand(totalPrice))

            applicationEventPublisher.publishEvent(RequestSucceededEvent(command.requestId))

        } catch (ex: Exception) {
            applicationEventPublisher.publishEvent(RequestFailedEvent(command.requestId))
        }
    }
}
