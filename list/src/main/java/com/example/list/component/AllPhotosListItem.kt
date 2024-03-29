package com.example.list.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.base.ui.AllPhotosData

@OptIn(ExperimentalCoilApi::class)
@Composable
fun AllPhotosListItem(
    allPhotosData: AllPhotosData,
    onClickToDetails : (AllPhotosData) -> Unit
) {
    val context = LocalContext.current
    val allPhotosImage = rememberImagePainter(
        data = allPhotosData.images
    ){
        crossfade(durationMillis = 2000)
        error(com.example.base.R.drawable.no_image_available_svg)
        placeholder(com.example.base.R.drawable.placeholder)
    }
    val painterState = allPhotosImage.state

    if (painterState is ImagePainter.State.Loading){
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.scale(.5f)
        )
    }


    val userNameIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(
            "https://unsplash.com/@${allPhotosData.userName}?utm_source=Jetpack_Compose_Picture_App&utm_medium=referral"
        )
    )

    val unsplashIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(
            "https://unsplash.com/?utm_source=Jetpack_Compose_Picture_App&utm_medium=referral"
        )
    )

    val sizeImage by remember { mutableStateOf(IntSize.Zero) }
    val scroll = rememberScrollState(0)



    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier.height(200.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = allPhotosImage,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clickable {
                    onClickToDetails(allPhotosData)
                }
            )
            
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    ),
                    startY = 400f,
//                    endY = sizeImage.height.toFloat()
                ))
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Photo by",
                            color = Color(0xFFc7cdd8),
                        )
                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = allPhotosData.userName,
                            color = Color(0xFFc7cdd8),
                            modifier = Modifier
                                .clickable {
                                startActivity(context, userNameIntent, null)
                                           }
                                .horizontalScroll(scroll)
                        )
                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = "on Unsplash",
                            color = Color(0xFFc7cdd8),
                            modifier = Modifier.clickable {
                                startActivity(context, unsplashIntent, null)
                            },
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Favorite,
                            contentDescription = "",
                            tint = Color.Red
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = allPhotosData.likes.toString(),
                            color = Color(0xFFc7cdd8),
                        )
                    }
                }
            }
        }
    }

    //for lazy vertical grid
    /**
     * val mod =
    modifier.run {
    if (paddings != null) {
    modifier.padding(paddings).clip(RoundedCornerShape(8.dp))
    } else {
    this
    }
    }

    val context = LocalContext.current
    val allPhotosImage = rememberImagePainter(
    data = allPhotosData.images
    ){
    crossfade(durationMillis = 2000)
    error(com.example.base.R.drawable.no_image_available_svg)
    placeholder(com.example.base.R.drawable.placeholder)
    }
    val painterState = allPhotosImage.state

    if (painterState is ImagePainter.State.Loading){
    CircularProgressIndicator(
    color = MaterialTheme.colors.primary,
    modifier = Modifier.scale(.5f)
    )
    }


    val userNameIntent = Intent(
    Intent.ACTION_VIEW,
    Uri.parse(
    "https://unsplash.com/@${allPhotosData.userName}?utm_source=Jetpack_Compose_Picture_App&utm_medium=referral"
    )
    )

    val unsplashIntent = Intent(
    Intent.ACTION_VIEW,
    Uri.parse(
    "https://unsplash.com/?utm_source=Jetpack_Compose_Picture_App&utm_medium=referral"
    )
    )

    val sizeImage by remember { mutableStateOf(IntSize.Zero) }
    val scroll = rememberScrollState(0)


    Card(
    modifier = mod,
    shape = RoundedCornerShape(8.dp),
    elevation = 4.dp
    ) {
    Box(
    modifier = Modifier.height(200.dp),
    contentAlignment = Alignment.BottomCenter
    ) {
    Image(
    painter = allPhotosImage,
    contentDescription = "",
    contentScale = ContentScale.Crop,
    modifier = Modifier.clickable {
    onClickToDetails(allPhotosData)
    }
    )

    Box(modifier = Modifier
    .fillMaxSize()
    .background(Brush.verticalGradient(
    colors = listOf(
    Color.Transparent,
    Color.Black
    ),
    startY = 400f,
    //                    endY = sizeImage.height.toFloat()
    ))
    )

    Box(
    modifier = Modifier
    .fillMaxWidth()
    .padding(16.dp),
    contentAlignment = Alignment.BottomStart
    ) {
    Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
    ) {
    Row(
    verticalAlignment = Alignment.CenterVertically
    ) {
    Text(
    text = "Photo by",
    color = Color(0xFFc7cdd8),
    )
    Spacer(modifier = Modifier.width(2.dp))

    Text(
    text = allPhotosData.userName,
    color = Color(0xFFc7cdd8),
    modifier = Modifier
    .clickable {
    startActivity(context, userNameIntent, null)
    }
    .horizontalScroll(scroll)
    )
    Spacer(modifier = Modifier.width(2.dp))

    Text(
    text = "on Unsplash",
    color = Color(0xFFc7cdd8),
    modifier = Modifier.clickable {
    startActivity(context, unsplashIntent, null)
    },
    )
    }

    Row(
    verticalAlignment = Alignment.CenterVertically
    ) {
    Icon(
    imageVector = Icons.Rounded.Favorite,
    contentDescription = "",
    tint = Color.Red
    )
    Spacer(modifier = Modifier.width(2.dp))
    Text(
    text = allPhotosData.likes.toString(),
    color = Color(0xFFc7cdd8),
    )
    }
    }
    }
    }
    }
     */
}