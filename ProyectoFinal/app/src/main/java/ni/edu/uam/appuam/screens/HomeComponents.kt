package ni.edu.uam.appuam.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Yard
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ni.edu.uam.appuam.ui.theme.Blanco
import ni.edu.uam.appuam.ui.theme.GrisOscuro
import ni.edu.uam.appuam.ui.theme.VerdeClaro
import ni.edu.uam.appuam.ui.theme.VerdeOscuro
import ni.edu.uam.appuam.ui.theme.VerdePrincipal

data class Consejo(
    val titulo: String,
    val descripcion: String
)

val consejosHabitU = listOf(
    Consejo(
        titulo = "Consejo del día",
        descripcion = "Usa una botella reutilizable para reducir residuos plásticos."
    ),
    Consejo(
        titulo = "Consejo del día",
        descripcion = "Apaga las luces cuando salgas de una habitación."
    ),
    Consejo(
        titulo = "Consejo del día",
        descripcion = "Lleva una bolsa reutilizable cuando vayas de compras."
    ),
    Consejo(
        titulo = "Consejo del día",
        descripcion = "Separa los residuos reciclables de los orgánicos."
    ),
    Consejo(
        titulo = "Consejo del día",
        descripcion = "Evita dejar cargadores conectados si no los estás usando."
    ),
    Consejo(
        titulo = "Consejo del día",
        descripcion = "Camina o usa bicicleta para trayectos cortos."
    ),
    Consejo(
        titulo = "Consejo del día",
        descripcion = "Reutiliza hojas de papel para apuntes o borradores."
    ),
    Consejo(
        titulo = "Consejo del día",
        descripcion = "Cierra el grifo mientras te cepillas los dientes."
    ),
    Consejo(
        titulo = "Consejo del día",
        descripcion = "Usa recipientes reutilizables en lugar de bolsas plásticas."
    ),
    Consejo(
        titulo = "Consejo del día",
        descripcion = "Cuida las áreas verdes y evita tirar basura en el campus."
    )
)
@Composable
fun DailyTipCard() {
    val consejo = remember {
        consejosHabitU.random()
    }

    TipCard(
        consejo = consejo
    )
}

@Composable
fun TipCard(
    consejo: Consejo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Blanco
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(VerdeClaro),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Yard,
                    contentDescription = "Consejo ecológico",
                    tint = VerdeOscuro,
                    modifier = Modifier.size(30.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = consejo.titulo,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = VerdeOscuro
                )

                Text(
                    text = consejo.descripcion,
                    style = MaterialTheme.typography.bodySmall,
                    color = GrisOscuro
                )
            }

            Icon(
                imageVector = Icons.Default.Eco,
                contentDescription = null,
                tint = VerdePrincipal
            )
        }
    }
}

@Composable
fun HomeOptionCard(
    title: String,
    description: String,
    icon: ImageVector,
    iconTint: Color,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(94.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Blanco.copy(alpha = 0.65f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconTint,
                    modifier = Modifier.size(36.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 18.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = GrisOscuro
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = GrisOscuro.copy(alpha = 0.75f)
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Ir a $title",
                tint = GrisOscuro
            )
        }
    }
}