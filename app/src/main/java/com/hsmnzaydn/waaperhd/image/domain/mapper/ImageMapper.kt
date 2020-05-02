import com.hsmnzaydn.waaperhd.image.data.entities.ImageResponse
import com.hsmnzaydn.waaperhd.image.domain.entities.Image

fun ImageResponse.toImageThumbNail() = Image.ThumbNailImage(
    id = this.imageId,
    imagePath = this.imagePath
)

fun ImageResponse.toImageDetail() = Image.ImageDetail(
    id = this.imageId,
    imagePath = this.imagePath
)