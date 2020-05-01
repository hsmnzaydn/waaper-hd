import com.hsmnzaydn.waaperhd.image.data.entities.ImageResponse
import com.hsmnzaydn.waaperhd.image.domain.entities.Image

fun ImageResponse.toImageThumbNail() = Image.ThumbNailImage(
    id = this.id?.let { it },
    imagePath = this.urls?.let { it.thumb }
)