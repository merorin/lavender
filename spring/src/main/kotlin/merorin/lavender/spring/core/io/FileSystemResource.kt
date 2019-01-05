package merorin.lavender.spring.core.io

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream

/**
 * Description:
 * @author guobin On date 2019/1/5.
 * @since jdk 1.8
 * @version 1.0
 */
class FileSystemResource(private val filePath : String) : Resource {

    private val file : File = File(this.filePath)

    @Throws(IOException::class)
    override fun getInputStream(): InputStream {
        return FileInputStream(this.file)
    }

    override fun getDescription() : String {
        return "file [${this.file.absolutePath}]"
    }
}