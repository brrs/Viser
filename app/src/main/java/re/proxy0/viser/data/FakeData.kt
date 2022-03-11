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
    return "Cras lacus tortor, luctus sit amet est vel, dignissim scelerisque erat. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Ut vel sagittis nisl. Quisque euismod venenatis scelerisque. Suspendisse interdum turpis vitae dolor gravida, at dignissim eros interdum. Integer elementum metus a lectus lacinia molestie. Mauris tristique fermentum nibh, vel efficitur justo. Aliquam convallis nisi id lobortis ornare. Cras porta sodales mauris vitae pharetra. Pellentesque consequat turpis erat, vel lobortis augue placerat quis. Donec varius posuere tellus non gravida. Cras semper efficitur malesuada.\n" +
            "\n" +
            "Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent iaculis velit vitae lobortis aliquet. Curabitur eu efficitur quam. Nulla ex nulla, tincidunt id facilisis sit amet, ultrices imperdiet enim. Nulla vitae tristique lacus. Proin consequat nisi ut maximus venenatis. Suspendisse nec libero ut est congue tempus non sed felis. Mauris nec pulvinar nisl, sed pharetra arcu. Curabitur nisl lorem, hendrerit eu elit ac, vulputate viverra nisl. Vestibulum ante dolor, malesuada id ante et, rutrum gravida ipsum. Nunc ac egestas elit, et posuere arcu. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed in mattis diam, vitae euismod metus. Nullam ac libero non dui aliquet consectetur in sed libero.\n" +
            "\n" +
            "Pellentesque pretium sem lobortis, facilisis erat eu, viverra mauris. Donec risus justo, vulputate ac fermentum vitae, ornare ac turpis. Suspendisse non elit sollicitudin, suscipit turpis id, luctus arcu. In posuere vestibulum quam, ut dignissim est pretium at. Ut euismod nulla vitae porttitor luctus. Nulla nec euismod arcu. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nunc eleifend elit euismod nunc dapibus, quis viverra orci laoreet. Nunc accumsan laoreet ligula sed pulvinar. Proin et tincidunt ex, vel facilisis nulla. Aenean gravida ultricies sapien sed tempor. Aliquam non feugiat ex, ut luctus nisl. Ut erat velit, egestas in elementum non, pellentesque a felis. Proin sapien odio, ullamcorper vitae dolor non, auctor tincidunt libero.\n" +
            "\n" +
            "Proin nec pharetra magna, quis pharetra enim. Donec at vehicula massa. Vestibulum vitae nibh blandit diam ullamcorper lacinia. Ut eros quam, dictum at ligula vel, auctor hendrerit sem. Fusce feugiat, dolor id congue euismod, neque lectus pulvinar sem, ac sollicitudin turpis risus vel turpis. Nam eget blandit diam. Integer interdum lorem at euismod pharetra. Etiam nulla nunc, tristique id elit quis, luctus suscipit justo. In vitae interdum elit, a pellentesque lorem. Aliquam quam lectus, ornare et nibh quis, ultricies porttitor magna. Morbi a elit urna. Phasellus varius leo at fermentum porttitor. Cras eu neque luctus, commodo magna nec, placerat ipsum. Morbi eu congue magna.\n" +
            "\n" +
            "Cras ligula sapien, ullamcorper id venenatis sit amet, pulvinar at tellus. Sed ut ullamcorper dui. Sed arcu ipsum, consequat nec finibus at, pretium eget turpis. Aenean in quam vel lacus dignissim pretium. Nulla vestibulum, est vitae iaculis euismod, enim lacus lacinia nisl, in rutrum ex nunc in sem. Duis tellus massa, fringilla a faucibus venenatis, convallis vitae magna. Maecenas ornare fermentum enim id venenatis. Etiam enim ante, feugiat at tincidunt eget, mattis at metus. Phasellus auctor ut odio et tincidunt. Cras pharetra augue et mauris porttitor tristique. Quisque sed eros nisl. Sed pellentesque malesuada dolor, ac tincidunt lorem condimentum sed. Interdum et malesuada fames ac ante ipsum primis in faucibus. Maecenas vel sagittis arcu. Nunc ut varius nunc. In facilisis ante est, cursus lobortis neque molestie in.\n" +
            "\n" +
            "Vivamus eu diam eget nisi porttitor feugiat. Ut ut faucibus tellus. Ut sem arcu, viverra a tincidunt quis, cursus ultrices purus. Curabitur iaculis convallis turpis eu condimentum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean finibus pretium commodo. Maecenas vulputate eu velit nec sollicitudin. Cras quis dui erat. Nulla et facilisis nisi. Nam lacinia, ligula nec pretium vulputate, nulla arcu consectetur turpis, sit amet vestibulum neque nisi eu nulla. Nunc in tincidunt mi, id luctus ligula. Integer vitae mattis mauris, quis rhoncus eros. In turpis est, vulputate at eros ut, suscipit molestie quam. Nulla nec nunc libero. Integer molestie nec metus ut facilisis. Nulla sed sapien libero.\n" +
            "\n" +
            "Proin posuere congue tempor. Phasellus suscipit nisl diam, et tristique purus auctor a. Maecenas bibendum condimentum est vel vulputate. Pellentesque aliquet, libero sit amet dignissim euismod, velit tortor efficitur quam, a varius odio massa eget diam. Morbi scelerisque, sem sit amet luctus laoreet, leo nisi facilisis diam, at luctus quam tellus placerat augue. Etiam nibh urna, tristique eu tristique vel, bibendum quis lacus. Morbi eu mattis elit, in finibus arcu. Nunc convallis rhoncus erat nec consequat. Sed finibus nibh sit amet elit lobortis, eu elementum ex volutpat. Maecenas ac consectetur augue, et facilisis mi. Ut ac dapibus dui. Sed vel magna non eros rhoncus faucibus molestie quis purus. Aliquam erat volutpat.\n" +
            "\n" +
            "Aliquam sollicitudin, arcu ut lobortis pharetra, urna mi sodales velit, sed rutrum massa ante vel massa. Phasellus vel nibh sem. Suspendisse magna augue, condimentum et dictum sed, molestie non libero. Donec scelerisque velit at suscipit rutrum. Aliquam malesuada neque id nibh commodo ultricies. Fusce purus ante, scelerisque at laoreet in, auctor at ligula. Sed ornare nisl eu diam imperdiet semper. Sed dictum condimentum aliquam. Cras ultrices porta elementum. Donec et convallis dui. Maecenas volutpat risus purus, sed tincidunt urna cursus vitae. Vivamus dapibus elit id massa pretium faucibus. Donec convallis, quam ac vulputate porttitor, urna ex molestie lorem, vitae pretium nulla justo ac ante. In faucibus tellus sed magna fringilla pharetra. Donec vestibulum lacus mi, ac condimentum nibh pharetra in. Mauris vel luctus ex.\n" +
            "\n" +
            "Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aliquam faucibus leo ac felis dictum, vel gravida nulla iaculis. Integer efficitur tincidunt quam, eget dictum urna fermentum eu. Nullam vehicula sodales lacinia. Sed vestibulum arcu pharetra eros eleifend, sit amet fringilla urna sollicitudin. In auctor mi quis sapien ultricies, non fermentum lectus consectetur. Nulla facilisi. Mauris a tortor ut velit semper mollis. Vestibulum gravida pretium nisi sit amet dapibus. Nam eget mauris tellus. Integer ipsum nunc, fermentum sed bibendum ut, gravida vitae eros. Fusce sit amet hendrerit erat.\n" +
            "\n" +
            "Suspendisse vitae justo interdum, semper nunc ac, hendrerit nulla. Integer faucibus consequat urna, ac ornare magna finibus et. Donec facilisis maximus elit, non ultrices mi vestibulum sed. Quisque dapibus enim at erat maximus, quis egestas diam porttitor. Vestibulum eu turpis massa. Cras dui nisl, sagittis id neque a, ultrices venenatis mi. Donec ut enim ut dolor placerat posuere non eu odio. Suspendisse et accumsan ante, id elementum tortor. Cras imperdiet blandit volutpat. In condimentum purus id lacus mattis, non interdum velit iaculis. Proin viverra hendrerit ipsum, a euismod mi tincidunt hendrerit. Ut cursus sapien libero, at laoreet quam suscipit vel. Vestibulum vehicula tellus nec massa laoreet, ut venenatis dui sollicitudin.\n" +
            "\n" +
            "Aenean rutrum libero nec sem varius interdum. Nulla dolor orci, cursus bibendum ligula ac, pretium hendrerit erat. Nam sed erat elementum, imperdiet urna quis, consequat elit. Integer cursus odio sapien, et aliquam arcu ornare in. Nullam pulvinar iaculis ipsum in lobortis. Curabitur imperdiet aliquet mollis. Ut a porttitor nisl, sed tincidunt arcu. In fermentum vel tortor ornare commodo.\n" +
            "\n" +
            "Nam vitae dolor tellus. Vestibulum neque odio, euismod gravida dolor quis, aliquet eleifend eros. Cras leo lorem, venenatis at mollis eget, vulputate et magna. In in ornare orci. Integer id laoreet elit. Suspendisse potenti. Ut suscipit, ipsum nec ornare iaculis, enim risus sagittis neque, feugiat interdum nibh dui id ante. Morbi egestas metus a dui dapibus pretium.\n" +
            "\n" +
            "Integer vitae scelerisque sem, ac eleifend ligula. Sed iaculis sagittis semper. Ut aliquet in mauris nec pellentesque. Quisque sit amet orci auctor, dictum diam id, ornare mauris. Sed ac est quis metus dignissim luctus quis nec massa. Suspendisse vel consequat enim. Sed mattis eget orci et efficitur. Mauris ut sem mi. Morbi ut nulla orci. Quisque eleifend massa purus, et gravida sapien consectetur in. Etiam id mattis odio. Proin eu vestibulum tellus, quis tristique ante. Interdum et malesuada fames ac ante ipsum primis in faucibus.\n" +
            "\n" +
            "Nunc eget odio suscipit, ultricies lacus eu, mattis nulla. Quisque finibus sapien a imperdiet faucibus. In malesuada in dolor non commodo. Morbi elementum suscipit turpis, id dignissim leo consequat at. Ut porttitor leo in velit feugiat elementum. Nullam venenatis nulla sed odio bibendum placerat. In volutpat orci quis volutpat venenatis. Maecenas ultricies eu magna eget efficitur. Aenean egestas tempus vulputate. Donec mi erat, viverra et dictum sit amet, auctor quis metus. Aenean nec commodo dolor. Fusce massa massa, lacinia sit amet vulputate sit amet, hendrerit vel ligula. Nam rutrum neque orci, quis elementum libero hendrerit et.\n" +
            "\n" +
            "Vestibulum lorem nisl, malesuada quis metus vitae, fringilla aliquet felis. Suspendisse ut nulla metus. Vivamus imperdiet condimentum sapien eu ornare. Donec feugiat sem in lectus scelerisque bibendum. Phasellus dictum felis enim, et dignissim ipsum dignissim iaculis. Donec gravida lectus ipsum, ac dapibus nisi elementum at. Fusce non nisi est.\n" +
            "\n" +
            "Suspendisse lobortis, metus et tincidunt placerat, mi odio efficitur libero, vitae scelerisque mauris quam sed dolor. Nunc orci orci, egestas eget nunc in, interdum fringilla dolor. Vestibulum maximus, dui a tincidunt blandit, augue velit suscipit lacus, sed semper sapien tortor ut augue. Curabitur id mi nunc. Curabitur posuere vel lacus non molestie. Duis vel accumsan eros. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec sodales purus mauris, ac fermentum neque auctor sed. Nam venenatis venenatis tellus, sed cursus lorem ornare molestie.\n" +
            "\n" +
            "Praesent fringilla pulvinar ultrices. Duis vulputate sapien ut dignissim ultricies. Phasellus in purus tincidunt, luctus elit a, tempor nisi. Nunc nec interdum lectus. Morbi finibus convallis porta. Proin gravida quam sit amet odio volutpat, a fermentum massa commodo. Nam a sapien ornare, sodales velit vitae, aliquam mauris. Maecenas eget venenatis dolor, at sollicitudin sapien.\n" +
            "\n" +
            "Pellentesque hendrerit pellentesque mauris, a pulvinar felis pulvinar a. Donec aliquet tellus purus, quis malesuada diam rutrum molestie. Sed sit amet erat posuere, porttitor ante ultricies, egestas orci. Proin pulvinar mi quis urna sollicitudin, sit amet sodales lectus pulvinar. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Duis tortor metus, blandit ut lacus id, congue tincidunt dolor. Fusce cursus felis a massa gravida fringilla."
}