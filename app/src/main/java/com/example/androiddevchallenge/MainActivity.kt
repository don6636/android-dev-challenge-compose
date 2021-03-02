/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.model.puppies
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlin.math.max

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }

    // Start building your app here!
    @Composable
    fun MyApp() {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold {
                Column {
                    Text(
                        text = "Adopt a lovely puppy",
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.End,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 64.dp
                        )
                    )

                    PuppyGrid(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight()
                    )

                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }

    @Composable
    fun StaggeredGrid(
        modifier: Modifier = Modifier,
        rows: Int = 3,
        content: @Composable () -> Unit
    ) {
        Layout(
            content = content,
            modifier = modifier
        ) { measurables, constraints ->
            val rowWidths = IntArray(rows) { 0 } // Keep track of the width of each row
            val rowHeights = IntArray(rows) { 0 } // Keep track of the height of each row

            // Don't constrain child views further, measure them with given constraints
            val placeables = measurables.mapIndexed { index, measurable ->
                val placeable = measurable.measure(constraints)

                // Track the width and max height of each row
                val row = index % rows
                rowWidths[row] += placeable.width
                rowHeights[row] = max(rowHeights[row], placeable.height)

                placeable
            }

            // Grid's width is the widest row
            val width = rowWidths.maxOrNull()?.coerceIn(constraints.minWidth, constraints.maxWidth)
                ?: constraints.minWidth
            // Grid's height is the sum of each row
            val height = rowHeights.sum().coerceIn(constraints.minHeight, constraints.maxHeight)

            // y co-ord of each row
            val rowY = IntArray(rows) { 0 }
            for (i in 1 until rows) {
                rowY[i] = rowY[i - 1] + rowHeights[i - 1]
            }
            layout(width, height) {
                // x co-ord we have placed up to, per row
                val rowX = IntArray(rows) { 0 }
                placeables.forEachIndexed { index, placeable ->
                    val row = index % rows
                    placeable.place(
                        x = rowX[row],
                        y = rowY[row]
                    )
                    rowX[row] += placeable.width
                }
            }
        }
    }

    @Composable
    fun PuppyPortrait(puppy: Puppy, onClick: () -> Unit) {
        Surface(
            modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = onClick),
            elevation = 4.dp,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = puppy.avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .requiredWidth(72.dp)
                        .requiredHeight(72.dp),
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = puppy.name,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.End,
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 6.dp
                        )
                    )
                    Text(
                        text = puppy.breed,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.End,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun PuppyGrid(modifier: Modifier) {
        StaggeredGrid(
            modifier = modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 8.dp),
        ) {
            puppies.forEach { puppy ->
                PuppyPortrait(puppy = puppy) {
                    startActivity(
                        Intent(this, PuppyPortraitActivity::class.java).apply {
                            putExtra(PUPPY, puppy)
                        }
                    )
                }
            }
        }
    }

    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun LightPreview() {
        MyTheme {
            MyApp()
        }
    }

    @Preview("Dark Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun DarkPreview() {
        MyTheme(darkTheme = true) {
            MyApp()
        }
    }
}
