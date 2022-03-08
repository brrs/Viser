package re.proxy0.viser.data

class Book(
    val id: Int,
    val name: String,
    val author: String,
    val genres: List<String>,
    val status: Status,
    val chapters: List<Chapter>,
    val description: String = getDescriptionText(),
    val coverUrl: String = "https://picsum.photos/id/".plus((1..1000).random()).plus("/200/")
) {
    enum class Status {
        COMPLETED, ONGOING
    }
}

class Chapter(
    val name: String,
    val date: String,
    val text: String
)

enum class Genre {
    Action, Detective, Magic, Sports, Thriller, Military, Pets, Mecha, Romance, Scifi, Seinen, Shounen, Comedy
}

class BookCollection(
    val name: String,
    val books: List<Book>
)

fun generateCollection(amount: Int): BookCollection = BookCollection(
    name = "Book collection",
    books = generateBooks(amount)
)

private fun generateBooks(amount: Int): List<Book> = List(amount) {
    Book(
        id = it,
        name = "Book $it",
        author = "Taras Shevchenko No. $it",
        genres = getRandomGenres(),
        status = Book.Status.values()[(1..2).random() - 1],
        chapters = generateChapters(amount * 3)
    )
}

fun generateBook(): Book = Book(
    id = 0,
    name = "Long, super long name, just like the Japanese love it so much. And a few more words.",
    author = "Taras Grygorovych Shevchenko",
    genres = getRandomGenres(),
    status = Book.Status.values()[(1..2).random() - 1],
    chapters = generateChapters(12)
)

private fun getRandomGenres(): List<String> = List((1..Genre.values().size).random() - 1) {
    Genre.values()[(1..Genre.values().size).random() - 1].toString()
}

private fun generateChapters(amount: Int): List<Chapter> = List(amount) {
    Chapter("Chapter $it", "12.12.2012", getChapterText())
}

private fun getDescription4LinesText(): String {
    return "First line. \n" +
            "Second line. \n" +
            "Third line. \n" +
            "Fourth line. \n"
}

private fun getDescription5LinesText(): String {
    return "First line. \n" +
            "Second line. \n" +
            "Third line. \n" +
            "Fourth line. \n" +
            "Fifth line. \n"
}

private fun getDescription6LinesText(): String {
    return "First line. \n" +
            "Second line. \n" +
            "Third line. \n" +
            "Fourth line. \n" +
            "Fifth line. \n" +
            "Sixth line. \n"
}

private fun getDescriptionLongSingleLineText(): String {
    return "This is quite a problem to solve, but just doing without greeking text won't fix it. Using test items of real content and data in designs will help, but there's no guarantee that every oddity will be found and corrected. Do you want to be sure? Then a prototype or beta site with real content published from the real CMS is needed—but you’re not going that far until you go through an initial design cycle. " +
            "Lorem Ipsum actually is usefull in the design stage as it focuses our attention on places where the content is a dynamic block coming from the CMS (unlike static content elements that will always stay the same.) Blocks of Lorem Ipsum with a character count range provide a obvious reminder to check and re-check that the design and the content model match up."
}

private fun getDescriptionText(): String {
    return "Copyright 2020 The Android Open Source Project\n" +
            "\n" +
            "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "you may not use this file except in compliance with the License.\n" +
            "You may obtain a copy of the License at\n" +
            "\n" +
            "    https://www.apache.org/licenses/LICENSE-2.0\n" +
            "\n" +
            "Unless required by applicable law or agreed to in writing, software\n" +
            "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "See the License for the specific language governing permissions and\n" +
            "limitations under the License.\n" +
            "\n" +
            "If you have a story to tell, knowledge to share, or a perspective to offer — welcome home. \n" +
            "It’s easy and free to post your thinking on any topic. \n" +
            "\n" +
            "Medium is an open platform where 170 million readers come to find insightful and dynamic thinking. \n" +
            "Here, expert and undiscovered voices alike dive into the heart of any topic and bring new ideas to the surface. \n" +
            "\n" +
            "Yes! you are right! This basically provides all the information regarding the size (and much more) of the composable to be drawn on screen. \n" +
            "We are going to use this property to see if our intended Textview is going to be exceeding a certain amount of lines!"
}

private fun getChapterText(): String {
    return "“Reporting! We found another victim at the location. There is no sign of the preparator on the scene!”\n" +
            "\n" +
            "Looking at the young man that was lying on the ground who was being examined by his colleague, the police officer reported his squad’s findings to his superior with a dark face.\n" +
            "\n" +
            "“Got it, call for an emergency ambulance and wait for reinforcements. I already called the department to send another squad. Once they arrive, take half of your squad and sweep the area on a larger radius along with them.”\n" +
            "\n" +
            "The young man had injuries of various degrees and shapes all over his body and his clothes were covered in blood. According to the quick examination they made, his injuries weren’t life-threatening and he seemed to have simply fainted, but even then, his face showed a pained expression that couldn’t be hidden by the injuries on his face.\n" +
            "\n" +
            "“Yes, sir!”\n" +
            "\n" +
            "Taking another look at the young man, the superior police officer let out a sigh as displeasure could be seen on his face, before muttering to himself, “I was going to be transferred soon, why is it that a serial kidnapper had to start his business now?!”\n" +
            "\n" +
            "In reality, he wanted to quickly catch the criminal behind this so that the streets could be safe again, but it wasn’t like he could do much. The criminal this time was simply too careful and always managed to escape after returning the mutilated victims. Unless a better force worked in this case, catching him didn’t seem possible…\n" +
            "\n" +
            "‘Just how many people will have to suffer until then?’\n" +
            "\n" +
            "As he looked down with gloomy thought, eyes like that of a hawk with slightly dilated pupils seemed to focus on him from a few hundred meters away.\n" +
            "\n" +
            "Below these eyes, a half-grin could be seen, but it quickly disappeared, as obvious displeasure took its place.\n" +
            "\n" +
            "“There isn’t as much ‘despair’ to enjoy as last time. Could it be that my ‘care’ has gotten old since it’s the third time? Do I have to give my next ‘prey’ the sweet salivation of death to get a better reaction?”\n" +
            "\n" +
            "Putting down his binoculars, a handsome man with short chestnut hair supported his arms on the fence of the rooftop as he pondered in deep thought.\n" +
            "\n" +
            "Inflicting injury and seeing the prey’s reaction to it was what he was most passionate about. However, death would rid him of that reaction, that was why he was hesitant about it…\n" +
            "\n" +
            "“Perhaps it’s about time, while I would hate to kill my ‘prey’ instead of continuously giving them my ‘care’, I have always wanted to taste the delicious ‘despair’ they would feel just at the moment of their death… I should get going soon so I can start choosing my lucky next ‘prey’!”\n" +
            "\n" +
            "The handsomeness could no longer be seen on the man’s face as he licked his lips with a malevolent expression on his face, many dark (happy) thoughts seemed to circulate in his mind.\n" +
            "\n" +
            "“Wow, you have already reached this stage! I guess I was really lucky to find you now.”\n" +
            "\n" +
            "Just as the man picked up his binoculars to take one last look so that he could leave, he was shocked to hear a voice that seemed to carry a hint of sarcasm from behind. He quickly turned around only for his shock to grow stronger!\n" +
            "\n" +
            "The source of this voice was none other than a youth covering his face with a smiling cat mask, he was wearing a windbreaker and had one hand in his pocket, seeming as nonchalant as ever. The man felt as if he had met some sort of stupid clown.\n" +
            "\n" +
            "“Huh? Who are you…? Rather, how did you get here?”\n" +
            "\n" +
            "The man originally wanted to play the ‘I don’t know anything’ act, but he already spoke too much, so he quickly switched his plan. He secretly put one hand in his pocket, he would make him the next ‘prey’ instead!\n" +
            "\n" +
            "However, he couldn’t be too careless since he didn’t have enough fun, it wasn’t time to get caught yet. He planned to chat with the youth so he could relax his guard even more. This way, the risk of him running away from fear before he ‘captured’ him would be almost null.\n" +
            "\n" +
            "The youth was obviously not ordinary and seemed to be sick in the head, so it didn’t seem to be that hard. In the worst-case scenario, he could still use that…\n" +
            "\n" +
            "“Well, it was a bit tricky since you were quite careful. I had to try a bit harder and wait longer, but it wasn’t that hard thanks to what I learned from my friends.”\n" +
            "\n" +
            "“Your friends?”\n" +
            "\n" +
            "The man panicked a bit as he thought to himself, ‘Could it be that his friends are skilled police detectives?’\n" +
            "\n" +
            "He was sure that such people shouldn’t get involved yet, his ‘care’ didn’t cross the line for now.\n" +
            "\n" +
            "“Yes, my friends Sherlock and C. Dupin!”\n" +
            "\n" +
            "“…”\n" +
            "\n" +
            "The man who was preparing what to say next, thinking how to get closer to the youth without being too suspicious, was stunned in place, this guy was really a mental patient! These were imaginary characters that could only be found in books and shows!\n" +
            "\n" +
            "‘This guy must be a gift for me! Seeing and feeling the ‘despair’ of such a special ‘prey’ will definitely be a delicacy!’\n" +
            "\n" +
            "The man’s thoughts already deviated as drool could be seen on the edge of his mouth; he almost couldn’t wait to catch this ‘special’ youth.\n" +
            "\n" +
            "“Oh, why do you seem so excited for? Don’t you realize that I’m here to catch you?”\n" +
            "\n" +
            "The masked youth took his hand out of his pocket as he aimed it towards the man as if he had a gun in hand, a slightly loud voice rang in the man’s ear.\n" +
            "\n" +
            "“Bang!”\n" +
            "\n" +
            "“Ah!”\n" +
            "\n" +
            "The man panicked for a second in fear as he tried to dodge, only to realize that the sound wasn’t that loud, thinking back to it… It was just an imitated sound!\n" +
            "\n" +
            "Looking at the youth’s empty gloved hands, he felt stupid for going along with this guy’s stupid trick, if his nerves weren’t tense because of the situation he wouldn’t have fallen for such a simple trick. An irritated expression already started to show on his face.\n" +
            "\n" +
            "Visit readlightnovel.me for extra chapters.\n" +
            "\n" +
            "“What do you think of my gun? Did it hurt that badly? It’s better not to move for now till the police come to help you.”\n" +
            "\n" +
            "The youth spoke in a serious voice as he looked at the irritated man, causing the man’s irritation to spike. Dealing with mentals wasn’t as easy as it seemed.\n" +
            "\n" +
            "“Is that so? What about helping me go to the hospital instead?”\n" +
            "\n" +
            "Since the youth was clearly a mental patient, the man decided to lower his self-esteem and play along; he would feel worse if this guy escaped instead.\n" +
            "\n" +
            "“Oh, sorry, but I can’t. It would be troublesome showing my face to the police. Besides, I also have something that I need to buy soon.”\n" +
            "\n" +
            "The man almost wanted to facepalm, the crazy youth neither seemed smart enough to talk normally nor stupid enough to be coaxed. Feeling his patience running out, he decided to just go with the last option.\n" +
            "\n" +
            "“Sigh, Fine! What about now?”\n" +
            "\n" +
            "The man’s expression turned to a malevolent grin once again as he quickly took out his hand out of his pocket, pointing something at the youth.\n" +
            "\n" +
            "He had obtained a gun through special means before he started ‘hunting’ to ensure his safety, now he could at least use it to scare the youth into getting ‘captured’ instead and get out of here quickly!\n" +
            "\n" +
            "“Hmmm, what do you mean by that?”\n" +
            "\n" +
            "Anger flashed in the man’s eyes, it seemed like this kid didn’t even fear his…\n" +
            "\n" +
            "‘Huh?’\n" +
            "The man looked at his hand in shock, his hand was empty just like the kid’s hand, the gun was still in his pocket.\n" +
            "\n" +
            "Quickly wanting to put his hand back in his pocket to take the gun, the guy lowered his arm… only to find that it fell back numb. He seemed to have lost control of his arms.\n" +
            "\n" +
            "“Phew that was close, the neuromuscular blocking drug has finally taken effect.”\n" +
            "\n" +
            "The youth let out a sigh of relief as he opened the rooftop door and went in, returning with a bag on his bag as he quickly walked towards the man who had already collapsed to the floor.\n" +
            "\n" +
            "“Wha… di…”\n" +
            "\n" +
            "The man tried to speak but he also lost feeling in his mouth, he could no longer speak. Only then when he felt helpless did he realize that there was a very thin needle stabbed into his arm.\n" +
            "\n" +
            "‘I… was played!’\n" +
            "\n" +
            "That was the last thought the man had before he started losing his ability to breathe, he was slowly losing consciousness. He only managed to hear the youth speak a few more words before he fainted\n" +
            "\n" +
            "“A crazy sadist like you should stay sleeping like that for a while, but it isn’t like I’m one to speak.”\n" +
            "\n" +
            "‘After all, this is just a new hobby that I’m trying out to control my reading addiction.’\n" +
            "\n" +
            "The youth bent down as he took out an oxygen cylinder, working it out before attaching it through a mask to the man’s face.\n" +
            "\n" +
            "“Don’t worry, I won’t let you die.. My new hobby isn’t killing, it’s catching criminals who go overboard.”"
}