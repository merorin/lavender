package lavender.merorin

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths

/**
 * @author bin.guo
 * On 2019-02-21
 */
class MainApplication {

    companion object {

        @JvmStatic
        fun main(args : Array<String>) {
            Files.newInputStream(Paths.get(args[0])).use { it.processFileAndAggregate() }.apply {
                Files.write(Paths.get(args[1]), this)
            }
        }

        private fun InputStream.processFileAndAggregate() : List<String> {
            return XSSFWorkbook(this).getSheetAt(0).filter {
                it.lastCellNum > 1
            }.mapNotNull {
                it.getCell(1)
            }.map {
                it.stringCellValue.toLowerCase()
            }.filter {
                it.isNotEmpty()
            }.flatMap {
                it.split(" ")
            }.filter {
                it.matches(Regex("^[a-z]+$"))
            }.groupBy {
                it
            }.map {
                Pair(it.key, it.value.size)
            }.sortedByDescending {
                it.second
            }.map {
                "${it.first} : ${it.second}"
            }
        }
    }
}