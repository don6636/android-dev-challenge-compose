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
package com.example.androiddevchallenge.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.example.androiddevchallenge.R
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class Puppy(
    @DrawableRes val avatar: Int,
    val name: String,
    val breed: String,
    val story: String = "Puppy's Story"
) : Parcelable

val puppies = listOf(
    Puppy(
        R.drawable.karma, "Karma", "Labrador Retriever",
        "Her owner could not keep her, so sweet Karma is now part of the Perfect Pet Rescue family. " +
            "This 4-month-old bundle of joy is looking for an active family with lots of room so she can run " +
            "and play to her heart’s delight. She currently has only her first set of vaccines " +
            "and has much too learn about being a perfect pet."
    ),
    Puppy(
        R.drawable.wafer, "Wafer", "Shepherd",
        "Wafer is a 8 week old Shepherd Mix looking for his forever home. " +
            "He will likely be a medium-large dog."
    ),
    Puppy(
        R.drawable.noelle, "Noelle", "Chihuahua",
        "Noelle is a puppy that was discovered in utero when we went to spay her mommy. " +
            "When we brought her in to be spayed, it was discovered she had 1 puppy in her. " +
            "Noelle and her mom (last photo) are amazing together. " +
            "We would really love for them to go to the same home."
    ),
    Puppy(
        R.drawable.leia, "Leia", "Terrier",
        "She is too small only want love."
    ),
    Puppy(
        R.drawable.knuckles, "Knuckles", "Bichon Frise",
        "Hello! My name is Knuckles and I'm an adorable 9-week-old male puppy looking for a home. " +
            "My mom is part bichon, but I am probably not hypoallergenic. My foster mom says " +
            "I'm deceptively calm, but will join the fray willingly. I look like a bruiser but I'm a gentle soul " +
            "and I cuddle with the best of them."
    ),
    Puppy(
        R.drawable.brando, "Brando", "Boxer",
        "Everyone welcome Brando! Brando is a 3-month-old male Boxer who was surrendered to " +
            "the rescue by his owner. He was pretty sick upon intake but after receiving some much-needed " +
            "medical care, he is flourishing. Brando is a confident, loving and playful boy who LOVES other dogs " +
            "and ALL people."
    ),
    Puppy(
        R.drawable.parma, "Parma", "Cattle Dog",
        "This beautiful litter of puppies came out of a dilapidated trailer home in Kern County. " +
            "We have rescued several dogs and puppies out of this environment. They all have needed " +
            "medical and emotional attention. We are thrilled that we got these beautiful puppies early " +
            "enough to be able to intervene on their care."
    ),
    Puppy(
        R.drawable.meadow, "Meadow", "Akita",
        "Meet Meadow! She’s an 8 week old Lab/Akita mix and will weigh around 55-65 lbs " +
            "when she’s full grown."
    ),
    Puppy(
        R.drawable.cato, "Cato", "Mountain Dog",
        "Shy little Cato is a bit of a blank slate and needs some basic training but he is full of life " +
            "and loves to play. He adores his person but is a little skittish and timid when on walks and " +
            "hears loud noises so he needs to be kept securely on leash at all times."
    )
)
