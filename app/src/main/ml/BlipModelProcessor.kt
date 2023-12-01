import android.content.Context
import com.example.vinonovi.ml.Blipmodel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer

class BlipModelProcessor(context: Context) {
    private val model = Blipmodel.newInstance(context)

    fun processImage(byteBuffer: ByteBuffer): TensorBuffer {
        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1, 3, 224, 224), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        // Runs model inference and gets result.
        val outputs = model.process(inputFeature0)
        return outputs.outputFeature0AsTensorBuffer
    }

    fun close() {
        model.close()
    }
}
