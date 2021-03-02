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

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Puppy

/**
 * @author Shaun
 * @date 21/3/2
 */

const val PUPPY = "puppy_adopted"

class PuppyPortraitActivity : AppCompatActivity() {
    private lateinit var adoptedPuppy: Puppy
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val puppy = intent.getParcelableExtra<Puppy>(PUPPY)
        if (puppy == null) {
            Toast.makeText(this, "Unexpected wrong.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        adoptedPuppy = puppy

        setContent {
            NewStory()
        }
    }

    @Composable
    fun NewStory() {
        MaterialTheme {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            Toast.makeText(this, "Puppy adopted ^_^", Toast.LENGTH_SHORT).show()
                            finish()
                        },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_child_friendly_24),
                            contentDescription = null
                        )
                    }
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(adoptedPuppy.avatar),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(180.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.requiredHeight(16.dp))

                    Text(text = adoptedPuppy.name, style = MaterialTheme.typography.h5)
                    Text(text = adoptedPuppy.breed, style = MaterialTheme.typography.h6)

                    Spacer(modifier = Modifier.requiredHeight(8.dp))

                    Text(text = adoptedPuppy.story, style = MaterialTheme.typography.body1)
                }
            }
        }
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        NewStory()
    }
}
