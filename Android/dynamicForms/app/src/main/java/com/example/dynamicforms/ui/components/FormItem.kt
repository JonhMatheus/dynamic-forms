package com.example.dynamicforms.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dynamicforms.R
import com.example.dynamicforms.data.model.FormModel
import com.example.dynamicforms.theme.Blue
import com.example.dynamicforms.theme.Purple
import com.example.dynamicforms.theme.White
import kotlin.random.Random
import java.util.UUID

@Composable
fun FormItem(
    form: FormModel,
    onTap: (Offset) -> Unit)
{
    Card (
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray.copy(alpha = 0.1f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 20.dp,20.dp,0.dp)
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    onTap(offset)
                }
            }
    ){
    ConstraintLayout(
        modifier = Modifier.padding(20.dp)
    ) {
        val(title, id, countFields ) = createRefs()

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.constrainAs(title){
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 3.dp)
            }
        ){
            Icon(
                imageVector = ImageVector.vectorResource(id= R.drawable.baseline_insert_drive_file_24),
                contentDescription = "Icon Form"
            )

            Spacer(
                modifier = Modifier.width(5.dp)
            )

            Text(
                text = form.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,

            )
        }

        //TODO: ADICIONAR ID PARA FORMS E FAZER RESPONSES BASEADOS NO ID
        Text(
            text = "5b0040ab-780e-4006-817e-3c3545ec3105",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(id){
                top.linkTo(title.bottom, 8.dp)
                start.linkTo(parent.start, margin = 3.dp)
            }
        )
        Row (

            modifier = Modifier.constrainAs(countFields){
                top.linkTo(id.bottom, 20.dp)
                start.linkTo(parent.start, margin = 3.dp)
                bottom.linkTo(parent.bottom, margin = 5.dp)
            }
        ){

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Purple)

            ){
                Text(
                    text = "${form.fields.count()} fields",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 7.dp, horizontal = 15.dp),
                    fontSize = 14.sp,
                    color = White

                )
            }
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Blue)

            ){
                //TODO: @GET RESPONSE E TRAZER COUNT FILTRANDO PELO ID DO FORM
                Text(
                    text = "20 submissions",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 7.dp, horizontal = 15.dp),
                    fontSize = 14.sp,
                    color = White

                )
            }
        }
    }
    }
}
