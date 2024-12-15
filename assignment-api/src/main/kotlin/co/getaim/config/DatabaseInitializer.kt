package co.getaim.config

import co.getaim.domain.securities.SecuritiesDomain
import org.springframework.boot.CommandLineRunner
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.sql.PreparedStatement

@Transactional
@Component
class DatabaseInitializer(
    private val jdbcTemplate: JdbcTemplate
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        securitiesBulkInsert(

            listOf(
                SecuritiesDomain(
                    id = 1,
                    securityName = "Security 1",
                    securityCode = "SEC1",
                    price = 100.toBigDecimal()
                ),
                SecuritiesDomain(
                    id = 2,
                    securityName = "Security 2",
                    securityCode = "SEC2",
                    price = 200.toBigDecimal()
                ),
                SecuritiesDomain(
                    id = 3,
                    securityName = "Security 3",
                    securityCode = "SEC3",
                    price = 300.toBigDecimal()
                ),
                SecuritiesDomain(
                    id = 4,
                    securityName = "Security 4",
                    securityCode = "SEC4",
                    price = 400.toBigDecimal()
                ),
                SecuritiesDomain(
                    id = 5,
                    securityName = "Security 5",
                    securityCode = "SEC5",
                    price = 500.toBigDecimal()
                ),
                SecuritiesDomain(
                    id = 6,
                    securityName = "Security 6",
                    securityCode = "SEC6",
                    price = 600.toBigDecimal()
                ),
                SecuritiesDomain(
                    id = 7,
                    securityName = "Security 7",
                    securityCode = "SEC7",
                    price = 700.toBigDecimal()
                ),
                SecuritiesDomain(
                    id = 8,
                    securityName = "Security 8",
                    securityCode = "SEC8",
                    price = 800.toBigDecimal()
                ),
                SecuritiesDomain(
                    id = 9,
                    securityName = "Security 9",
                    securityCode = "SEC9",
                    price = 900.toBigDecimal()
                ),
                SecuritiesDomain(
                    id = 10,
                    securityName = "Security 10",
                    securityCode = "SEC10",
                    price = 1000.toBigDecimal()
                )
            )
        )

    }

    private fun securitiesBulkInsert(values: List<SecuritiesDomain>) : Long?{
        val sql =
            """
            | INSERT INTO securities (security_name, security_code, price, id)
            | VALUES (?, ?, ?, ?)
            """.trimMargin()

        jdbcTemplate.batchUpdate(
            sql,
            object : BatchPreparedStatementSetter {
                override fun setValues(ps: PreparedStatement, i: Int) {
                    val securities = values[i]
                    ps.setString(1, securities.securityName)
                    ps.setString(2, securities.securityCode)
                    ps.setBigDecimal(3, securities.price)
                    ps.setLong(4, securities.id)
                }

                override fun getBatchSize(): Int {
                    return values.size
                }
            },
        )

        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long::class.java)!!
    }
}