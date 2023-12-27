package com.example.cheerup.database

import com.example.cheerup.dao.QuoteDao
import com.example.cheerup.entities.Quote
import com.example.cheerup.models.Category

class DatabaseInitializer(private val quoteDao: QuoteDao) {
    suspend fun populateQuotes() {
        val quotesList = listOf(
            Quote(category = "Resilience", text = "Life is not about waiting for the storm to pass, but about learning to dance in the rain"),
            Quote(category = "Resilience", text = "The strongest people are not those who show strength in front of us but those who win battles we know nothing about"),
            Quote(category = "Resilience", text = "The oak fought the wind and was broken, the willow bent when it must and survived"),
            Quote(category = "Resilience", text = "Resilience is accepting your new reality, even if it's less good than the one you had before"),
            Quote(category = "Resilience", text = "It's not the load that breaks you; it's the way you carry it"),
            Quote(category = "Resilience", text = "The only way that we can live is if we grow. The only way we can grow is if we change"),
            Quote(category = "Resilience", text = "You may have to fight a battle more than once to win it"),
            Quote(category = "Resilience", text = "When everything seems to be going against you, remember that the airplane takes off against the wind, not with it"),
            Quote(category = "Resilience", text = "Don't watch the clock; do what it does. Keep going"),
            Quote(category = "Resilience", text = "The difference between a successful person and others is not a lack of strength, not a lack of knowledge, but rather a lack in will"),

            Quote(category = "Persevere",text="Perseverance is not a long race; it's many short races one after another."),
            Quote(category = "Persevere",text="The difference between a successful person and others is not a lack of strength, not a lack of knowledge, but rather a lack in will."),
            Quote(category = "Persevere",text="Perseverance is not just the willingness to work hard. It's that plus the willingness to be stubborn about your own belief in yourself"),
            Quote(category = "Persevere",text="It's not that I'm so smart; it's just that I stay with problems longer"),
            Quote(category = "Persevere",text="Success is no accident. It is hard work, perseverance, learning, studying, sacrifice, and most of all, love of what you are doing"),
            Quote(category = "Persevere",text="I do not think that there is any other quality so essential to success of any kind as the quality of perseverance. It overcomes almost everything, even nature"),
            Quote(category = "Persevere",text="What is not started today is never finished tomorrow."),
            Quote(category = "Persevere",text="The only limit to our realization of tomorrow will be our doubts of today"),
            Quote(category = "Persevere",text="Many of life's failures are people who did not realize how close they were to success when they gave up"),
            Quote(category = "Persevere",text="Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time."),

            Quote(category = "Hope",text = "Hope is the thing with feathers that perches in the soul and sings the tune without the words and never stops at all."),
            Quote(category = "Hope",text = "Hope is being able to see that there is light despite all of the darkness."),
            Quote(category = "Hope",text = "Where there is hope, there is faith. Where there is faith, miracles happen."),
            Quote(category = "Hope",text = "Hope is important because it can make the present moment less difficult to bear. If we believe that tomorrow will be better, we can bear a hardship today."),
            Quote(category = "Hope",text = "Hope is a waking dream."),
            Quote(category = "Hope",text = "When the world says, 'Give up,' Hope whispers, 'Try it one more time.'"),
            Quote(category = "Hope",text = "Hope is not pretending that troubles don't exist. It is the hope that they won't last forever. That hurts will be healed, and difficulties overcome."),
            Quote(category = "Hope",text = "Hope is the only thing stronger than fear."),
            Quote(category = "Hope",text = "Learn from yesterday, live for today, hope for tomorrow."),
            Quote(category = "Hope",text = "Hope is the heartbeat of the soul."),

            Quote(category = "Stress & Anxiety", text = "Every moment you get upset, you're wasting precious time. Time you can never get back."),
            Quote(category = "Stress & Anxiety", text = "It's not stress that kills us, it is our reaction to it."),
            Quote(category = "Stress & Anxiety", text = "You don't have to control your thoughts; you just have to stop letting them control you."),
            Quote(category = "Stress & Anxiety", text = "Stress is like a rocking chair. It gives you something to do but gets you nowhere."),
            Quote(category = "Stress & Anxiety", text = "Don't stress over what you can't control."),
            Quote(category = "Stress & Anxiety", text = "Stress is an alarm clock that lets you know you've attached to something not true for you."),
            Quote(category = "Stress & Anxiety", text = "If you want to conquer the anxiety of life, live in the moment, live in the breath."),
            Quote(category = "Stress & Anxiety", text = "Do not anticipate trouble or worry about what may never happen. Keep in the sunlight." ),
            Quote(category = "Stress & Anxiety", text = "The greatest weapon against stress is our ability to choose one thought over another."),
            Quote(category = "Stress & Anxiety", text = "In times of stress, the best thing we can do for each other is to listen with our ears and our hearts and to be assured that our questions are just as important as our answers."),

            Quote(category = "Work Inspiration", text = "Success is not final, failure is not fatal: It is the courage to continue that counts."),
            Quote(category = "Work Inspiration", text = "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work."),
            Quote(category = "Work Inspiration", text = "The only way to do great work is to love what you do."),
            Quote(category = "Work Inspiration", text = "The only limit to our realization of tomorrow will be our doubts of today."),
            Quote(category = "Work Inspiration", text = "Opportunities don't happen. You create them."),
            Quote(category = "Work Inspiration", text = "Don't be afraid to give up the good to go for the great."),
            Quote(category = "Work Inspiration", text = "Believe you can and you're halfway there."),
            Quote(category = "Work Inspiration", text = "The future belongs to those who believe in the beauty of their dreams."),
            Quote(category = "Work Inspiration", text = "The only place where success comes before work is in the dictionary."),
            Quote(category = "Work Inspiration", text = "Hard work beats talent when talent doesn't work hard."),

            Quote(
                category = "Happiness",
                text = "The greatest happiness you can have is knowing that you do not necessarily require happiness."
            ),
            Quote(category = "Happiness", text = "Happiness is not by chance, but by choice."),
            Quote(category = "Happiness", text = "The only joy in the world is to begin."),
            Quote(
                category = "Happiness",
                text = "The happiness of your life depends upon the quality of your thoughts."
            ),
            Quote(category = "Happiness", text = "The purpose of our lives is to be happy."),
            Quote(
                category = "Happiness",
                text = "Success is not the key to happiness. Happiness is the key to success. If you love what you are doing, you will be successful."
            ),
            Quote(
                category = "Happiness",
                text = "Count your age by friends, not years. Count your life by smiles, not tears."
            ),
            Quote(
                category = "Happiness",
                text = "Happiness is not something ready made. It comes from your own actions."
            ),
            Quote(
                category = "Happiness",
                text = "The best way to cheer yourself up is to try to cheer somebody else up."
            ),
            Quote(
                category = "Happiness",
                text = "Be happy with what you have. Be excited about what you want."
            ),

            Quote(
                category = "Positive Thoughts",
                text = "Your mind is a powerful thing. When you fill it with positive thoughts, your life will start to change."
            ),
            Quote(
                category = "Positive Thoughts",
                text = "Think positive, and positive things will happen."
            ),
            Quote(
                category = "Positive Thoughts",
                text = "Positive thinking will let you do everything better than negative thinking will."
            ),
            Quote(
                category = "Positive Thoughts",
                text = "A strong, positive self-image is the best possible preparation for success."
            ),
            Quote(
                category = "Positive Thoughts",
                text = "You can, you should, and if you're brave enough to start, you will."
            ),
            Quote(
                category = "Positive Thoughts",
                text = "Surround yourself with positive people and situations, and avoid negativity."
            ),
            Quote(
                category = "Positive Thoughts",
                text = "Your attitude determines your direction."
            ),
            Quote(
                category = "Positive Thoughts",
                text = "What consumes your mind controls your life."
            ),
            Quote(
                category = "Positive Thoughts",
                text = "Stay positive, work hard, and make it happen."
            ),
            Quote(
                category = "Positive Thoughts",
                text = "The only limit to our realization of tomorrow will be our doubts of today."
            ),


            Quote(
                category = "Gratitude",
                text = "Gratitude makes sense of our past, brings peace for today, and creates a vision for tomorrow."
            ),
            Quote(
                category = "Gratitude",
                text = "The more you are in a state of gratitude, the more you will attract things to be grateful for."
            ),
            Quote(
                category = "Gratitude",
                text = "Gratitude turns what we have into enough, and more. It turns denial into acceptance, chaos into order, confusion into clarity."
            ),
            Quote(
                category = "Gratitude",
                text = "Gratitude is the fairest blossom which springs from the soul."
            ),
            Quote(
                category = "Gratitude",
                text = "Gratitude is not only the greatest of virtues but the parent of all others."
            ),
            Quote(
                category = "Gratitude",
                text = "The thankful receiver bears a plentiful harvest."
            ),
            Quote(
                category = "Gratitude",
                text = "Gratitude is the healthiest of all human emotions. The more you express gratitude for what you have, the more likely you will have even more to express gratitude for."
            ),
            Quote(category = "Gratitude", text = "Gratitude unlocks the fullness of life."),
            Quote(
                category = "Gratitude",
                text = "Let us be grateful to the people who make us happy; they are the charming gardeners who make our souls blossom."
            ),
            Quote(
                category = "Gratitude",
                text = "Gratitude is the most exquisite form of courtesy."
            ),

            Quote(
                category = "Peace",
                text = "Peace is not merely a distant goal, but the journey itself."
            ),
            Quote(
                category = "Peace",
                text = "If you want to make peace with your enemy, you have to work with your enemy. Then he becomes your partner."
            ),
            Quote(category = "Peace", text = "Peace begins with a smile."),
            Quote(
                category = "Peace",
                text = "When the power of love overcomes the love of power, the world will know peace."
            ),
            Quote(
                category = "Peace",
                text = "An eye for an eye only ends up making the whole world blind."
            ),
            Quote(
                category = "Peace",
                text = "Better than a thousand hollow words is one word that brings peace."
            ),
            Quote(
                category = "Peace",
                text = "Those who are free of resentful thoughts surely find peace."
            ),
            Quote(
                category = "Peace",
                text = "If you want peace, you don't talk to your friends. You talk to your enemies."
            ),
            Quote(category = "Peace", text = "Nobody can bring you peace but yourself."),
            Quote(
                category = "Peace",
                text = "The most valuable possession you can own is an open heart. The most powerful weapon you can be is an instrument of peace."
            ),
        )

        quoteDao.insertQuotes(quotesList)
    }
}