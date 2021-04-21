# Kotlin - MVVM - Rxjava - Dagger - Paging Infinite Scrolling
A Photo list sample that retrieves pictures from Pixabay site
 
<ul>
<li>The application supports both portrait and landscape mode </li>
<li>The user is able to search for images entering a search term with at least 3  characters </li>
<li>When the app starts it triggers a search for the string "flowers"</li>
<li>When an item in the result list gets clicked, a new detail screen will be opened</li>

</ul>

<h2>What is used in project? </h2>
<ol>
 <li><strong>Kotlin</strong></li>
<li><strong>Clean Architecture</strong>: An app using layered architecture based on <a href="http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html" rel="nofollow">Clean Architecture by Uncle Bob</a>.</li>
<li><strong>Retrofit</strong>: To retrieve data from the network</li>
<li><strong>Architecture components and using MVVM</strong>: LiveData and ViewModel</li>
<li><strong>Paging library</strong>: Using PagedList to fetch more data from network.</li>
<li><strong>Navigation component</strong>: At last it's settled, single activity is what's Google recommend now. Navigation editor makes things easy for us to design navigation path of our app.</li>
<li><strong>Dagger 2</strong>: Using Dependency injection to manage the dependencies in an optimal way.</li>
<li><strong>Glide</strong>: To view images from the network </li>
</ol>
