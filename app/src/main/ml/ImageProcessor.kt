import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.ops.ResizeOp

class ImageProcessor {
    val imageProcessor = ImageProcessor.Builder()
        .add(NormalizeOp(0.0f, 255.0f))  // 이 줄 추가 안해서 입력값 달랐음 (1, 150, 150, 3)
//        .add(TransformToGrayScaleOp()) // 회색조 이미지, 라이브러리 tensorflow lite support 필요
        .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
        .build()
}