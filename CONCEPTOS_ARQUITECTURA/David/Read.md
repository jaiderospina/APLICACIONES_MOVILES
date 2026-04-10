  <h1>PokeList</h1>
  <p class="header-sub">Documentación técnica del proyecto Android que consume la PokéAPI usando Retrofit, Corrutinas y arquitectura MVVM con ViewModel + LiveData.</p>
  <div class="pill-row">
    <div class="pill"><span class="dot"></span>API activa</div>
    <div class="pill"><span class="dot"></span>Sin API key</div>
    <div class="pill"><span class="dot"></span>Llamadas paralelas</div>
    <div class="pill"><span class="dot"></span>Sin memory leaks</div>
  </div>
</div>

<section>
  <div class="section-label">Descripción</div>
  <h2>¿Qué hace la app?</h2>
  <p>Al abrirse, PokeList ejecuta la siguiente secuencia de forma automática:</p>
  <div class="flow">
    <div class="flow-step"><div class="flow-num">1</div><div><div class="flow-title">Indicador de carga</div><div class="flow-desc">Se muestra un <code>ProgressBar</code> mientras se esperan los datos de la red.</div></div></div>
    <div class="flow-step"><div class="flow-num">2</div><div><div class="flow-title">Consulta la lista</div><div class="flow-desc">Llama a <code>GET /pokemon?limit=20</code> para obtener los primeros 20 Pokémon.</div></div></div>
    <div class="flow-step"><div class="flow-num parallel">∥</div><div><div class="flow-title">Consulta 20 detalles en paralelo</div><div class="flow-desc">Usa <code>async/awaitAll</code> para pedir el detalle de cada Pokémon simultáneamente.</div></div></div>
    <div class="flow-step"><div class="flow-num">3</div><div><div class="flow-title">Muestra la lista</div><div class="flow-desc">Renderiza tarjetas con nombre, número, tipos, HP, ATK, DEF, altura y peso.</div></div></div>
    <div class="flow-step"><div class="flow-num">!</div><div><div class="flow-title">Manejo de errores</div><div class="flow-desc">Si falla la red, muestra un mensaje y un botón para reintentar.</div></div></div>
  </div>
</section>

<section>
  <div class="section-label">Stack</div>
  <h2>Tecnologías usadas</h2>
  <div class="table-wrap">
    <table>
      <thead><tr><th>Librería</th><th>Versión</th><th>Para qué se usa</th></tr></thead>
      <tbody>
        <tr><td>Retrofit 2</td><td><span class="ver">2.9.0</span></td><td>Cliente HTTP que realiza las llamadas REST a la API</td></tr>
        <tr><td>Converter-Gson</td><td><span class="ver">2.9.0</span></td><td>Convierte el JSON de la API en objetos Kotlin automáticamente</td></tr>
        <tr><td>OkHttp Logging</td><td><span class="ver">4.12.0</span></td><td>Imprime en Logcat cada llamada HTTP (útil en debug)</td></tr>
        <tr><td>Corrutinas Kotlin</td><td><span class="ver">1.7.3</span></td><td>Ejecuta la red en segundo plano sin bloquear la UI</td></tr>
        <tr><td>ViewModel</td><td><span class="ver">2.7.0</span></td><td>Separa la lógica de negocio de la UI, sobrevive rotaciones</td></tr>
        <tr><td>LiveData</td><td><span class="ver">2.7.0</span></td><td>Notifica a la Activity cuando el estado cambia</td></tr>
        <tr><td>Glide</td><td><span class="ver">4.16.0</span></td><td>Descarga y cachea imágenes desde URL de forma asíncrona</td></tr>
        <tr><td>ViewBinding</td><td><span class="ver">—</span></td><td>Acceso seguro a vistas XML sin usar <code>findViewById</code></td></tr>
        <tr><td>DiffUtil</td><td><span class="ver">—</span></td><td>Actualiza el RecyclerView solo en los ítems que cambiaron</td></tr>
      </tbody>
    </table>
  </div>
</section>

<section>
  <div class="section-label">Organización</div>
  <h2>Estructura del proyecto</h2>
  <div class="tree">
    <div><span class="folder">app/src/main/</span></div>
    <div>&nbsp;&nbsp;<span class="folder">java/com/example/pokelist/</span></div>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;<span class="folder">model/</span></div>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="file">Pokemon.kt</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="desc">← Data classes que mapean el JSON</span></div>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;<span class="folder">network/</span></div>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="file">ApiService.kt</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="desc">← Endpoints de la PokéAPI</span></div>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="file">RetrofitClient.kt</span>&nbsp;&nbsp;&nbsp;<span class="desc">← Singleton que configura Retrofit</span></div>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;<span class="folder">adapter/</span></div>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="file">PokemonAdapter.kt</span>&nbsp;&nbsp;&nbsp;<span class="desc">← Conecta la lista con el RecyclerView</span></div>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;<span class="folder">ui/</span></div>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="file">MainActivity.kt</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="desc">← Muestra la UI y observa el ViewModel</span></div>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="file">MainViewModel.kt</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="desc">← Lógica de red y gestión de estado</span></div>
    <div>&nbsp;&nbsp;<span class="folder">res/layout/</span></div>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;<span class="file">activity_main.xml</span>&nbsp;&nbsp;&nbsp;<span class="desc">← Layout principal</span></div>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;<span class="file">item_pokemon.xml</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="desc">← Tarjeta de cada Pokémon</span></div>
    <div>&nbsp;&nbsp;<span class="folder">res/values/</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="desc">← colors, strings, themes</span></div>
  </div>
</section>

<hr />

<section>
  <div class="section-label">Archivo 1 / 6</div>
  <h2>Modelo de datos — <code>model/Pokemon.kt</code></h2>
  <p>Define las clases que representan el JSON que devuelve la API. <strong>Gson</strong> lee la respuesta y rellena estas clases automáticamente — no hay que parsear el JSON a mano.</p>

  <h3>Primera llamada — lista paginada</h3>
  <pre><span class="lang-tag">JSON</span><code>{
  <span class="st">"count"</span>: 1302,
  <span class="st">"results"</span>: [
    { <span class="st">"name"</span>: <span class="st">"bulbasaur"</span>, <span class="st">"url"</span>: <span class="st">"https://pokeapi.co/api/v2/pokemon/1/"</span> },
    { <span class="st">"name"</span>: <span class="st">"ivysaur"</span>,   <span class="st">"url"</span>: <span class="st">"https://pokeapi.co/api/v2/pokemon/2/"</span> }
  ]
}</code></pre>
  <p>→ Mapeado a <code>PokemonListResponse</code> y <code>PokemonEntry</code>. La clase incluye helpers para no repetir lógica en el Adapter:</p>
  <pre><span class="lang-tag">Kotlin</span><code><span class="kw">fun</span> <span class="fn">getId()</span>: Int            <span class="cm">// Extrae el ID desde la URL: ".../pokemon/25/" → 25</span>
<span class="kw">fun</span> <span class="fn">getSpriteUrl()</span>: String   <span class="cm">// Construye la URL de imagen usando el ID</span>
<span class="kw">fun</span> <span class="fn">getDisplayName()</span>: String  <span class="cm">// Capitaliza: "bulbasaur" → "Bulbasaur"</span></code></pre>

  <h3>Segunda llamada — detalle completo</h3>
  <pre><span class="lang-tag">JSON</span><code>{
  <span class="st">"id"</span>: 1, <span class="st">"name"</span>: <span class="st">"bulbasaur"</span>, <span class="st">"height"</span>: 7, <span class="st">"weight"</span>: 69,
  <span class="st">"types"</span>: [{ <span class="st">"type"</span>: { <span class="st">"name"</span>: <span class="st">"grass"</span> } }],
  <span class="st">"stats"</span>: [{ <span class="st">"base_stat"</span>: 45, <span class="st">"stat"</span>: { <span class="st">"name"</span>: <span class="st">"hp"</span> } }],
  <span class="st">"sprites"</span>: { <span class="st">"other"</span>: { <span class="st">"official-artwork"</span>: { <span class="st">"front_default"</span>: <span class="st">"https://..."</span> } } }
}</code></pre>
  <p>→ Mapeado a <code>PokemonDetail</code>. La anotación <code>@SerializedName("official-artwork")</code> permite mapear claves JSON con guiones, que Kotlin no acepta como nombres de campo directos.</p>
</section>

<section>
  <div class="section-label">Archivo 2 / 6</div>
  <h2>Interfaz de red — <code>network/ApiService.kt</code></h2>
  <p>Define <strong>qué endpoints existen</strong> en la API. Retrofit lee esta interfaz y genera todo el código HTTP automáticamente.</p>
  <pre><span class="lang-tag">Kotlin</span><code><span class="kw">interface</span> <span class="cl">ApiService</span> {

    <span class="an">@GET</span>(<span class="st">"pokemon"</span>)               <span class="cm">// → GET https://pokeapi.co/api/v2/pokemon</span>
    <span class="kw">suspend fun</span> <span class="fn">getPokemonList</span>(
        <span class="an">@Query</span>(<span class="st">"limit"</span>)  limit:  Int,  <span class="cm">// ?limit=20</span>
        <span class="an">@Query</span>(<span class="st">"offset"</span>) offset: Int   <span class="cm">// &amp;offset=0</span>
    ): Response&lt;<span class="cl">PokemonListResponse</span>&gt;

    <span class="an">@GET</span>(<span class="st">"pokemon/{id}"</span>)          <span class="cm">// → GET https://pokeapi.co/api/v2/pokemon/1</span>
    <span class="kw">suspend fun</span> <span class="fn">getPokemonDetail</span>(
        <span class="an">@Path</span>(<span class="st">"id"</span>) id: Int
    ): Response&lt;<span class="cl">PokemonDetail</span>&gt;
}</code></pre>
  <div class="table-wrap">
    <table>
      <thead><tr><th>Anotación</th><th>Qué hace</th></tr></thead>
      <tbody>
        <tr><td><code>@GET</code></td><td>Define el path relativo a la base URL</td></tr>
        <tr><td><code>@Query</code></td><td>Añade parámetros a la URL: <code>?limit=20&amp;offset=0</code></td></tr>
        <tr><td><code>@Path</code></td><td>Reemplaza <code>{id}</code> en la URL por el valor del parámetro</td></tr>
        <tr><td><code>suspend</code></td><td>Permite ejecutar la función dentro de una corrutina</td></tr>
        <tr><td><code>Response&lt;T&gt;</code></td><td>Envuelve el resultado para leer el código HTTP y el body por separado</td></tr>
      </tbody>
    </table>
  </div>
</section>

<section>
  <div class="section-label">Archivo 3 / 6</div>
  <h2>Cliente Retrofit — <code>network/RetrofitClient.kt</code></h2>
  <p>Configura y crea la instancia de Retrofit. Al ser un <code>object</code> de Kotlin actúa como <strong>singleton</strong> — solo existe una instancia en toda la app.</p>
  <pre><span class="lang-tag">Kotlin</span><code><span class="kw">object</span> <span class="cl">RetrofitClient</span> {

    <span class="kw">private const val</span> BASE_URL = <span class="st">"https://pokeapi.co/api/v2/"</span>

    <span class="kw">val</span> api: <span class="cl">ApiService</span> <span class="kw">by lazy</span> {   <span class="cm">// Se construye solo cuando se necesita</span>
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(<span class="fn">GsonConverterFactory.create</span>())
            .build()
            .<span class="fn">create</span>(<span class="cl">ApiService</span>::<span class="kw">class</span>.java)
    }
}</code></pre>
  <div class="callout">
    <div class="callout-icon">💡</div>
    <p><strong>by lazy</strong> — Retrofit no se construye al arrancar la app, sino la primera vez que algo llama a <code>RetrofitClient.api</code>. Esto reduce el tiempo de inicio de la aplicación.</p>
  </div>
</section>

<section>
  <div class="section-label">Archivo 4 / 6</div>
  <h2>ViewModel — <code>ui/MainViewModel.kt</code></h2>
  <p>Es el componente central. Ejecuta las llamadas de red de forma segura y mantiene el estado de la UI.</p>

  <h3>Estado con Sealed Class</h3>
  <pre><span class="lang-tag">Kotlin</span><code><span class="kw">sealed class</span> <span class="cl">UiState</span> {
    <span class="kw">object</span>     <span class="cl">Loading</span>                               : <span class="cl">UiState</span>()
    <span class="kw">data class</span> <span class="cl">Success</span>(<span class="kw">val</span> pokemons: List&lt;<span class="cl">PokemonDetail</span>&gt;) : <span class="cl">UiState</span>()
    <span class="kw">data class</span> <span class="cl">Error</span>(<span class="kw">val</span> message: String)               : <span class="cl">UiState</span>()
}</code></pre>

  <h3>Llamadas paralelas con async/awaitAll</h3>
  <pre><span class="lang-tag">Kotlin</span><code>viewModelScope.<span class="fn">launch</span>(Dispatchers.IO) {
    <span class="cm">// 1. Una llamada para la lista</span>
    <span class="kw">val</span> entries = RetrofitClient.api.<span class="fn">getPokemonList</span>(limit = <span class="nm">20</span>).body()!!.results

    <span class="cm">// 2. 20 llamadas simultáneas con async/awaitAll</span>
    <span class="kw">val</span> details = entries
        .<span class="fn">map</span> { entry -&gt; <span class="fn">async</span> { RetrofitClient.api.<span class="fn">getPokemonDetail</span>(entry.<span class="fn">getId</span>()).body() } }
        .<span class="fn">awaitAll</span>()
        .<span class="fn">filterNotNull</span>()

    _uiState.<span class="fn">postValue</span>(<span class="cl">UiState</span>.<span class="cl">Success</span>(details))
}</code></pre>

  <div class="callout">
    <div class="callout-icon">⚡</div>
    <p><strong>¿Por qué async/awaitAll y no un bucle for?</strong><br/>Con <code>for</code> las 20 llamadas son secuenciales (~20 seg). Con <code>async/awaitAll</code> todas se lanzan al mismo tiempo y se espera a la más lenta (~1-2 seg).</p>
  </div>
  <div class="callout">
    <div class="callout-icon">🔄</div>
    <p><strong>¿Por qué ViewModel y no la Activity?</strong><br/>Al rotar el teléfono Android destruye y recrea la Activity, cancelando cualquier llamada de red que hubiera dentro. El ViewModel sobrevive a las rotaciones — los datos no se pierden ni se vuelven a pedir.</p>
  </div>
</section>

<section>
  <div class="section-label">Archivo 5 / 6</div>
  <h2>Adapter — <code>adapter/PokemonAdapter.kt</code></h2>
  <p>Conecta la lista de <code>PokemonDetail</code> con las vistas del RecyclerView usando <strong>DiffUtil</strong> en lugar del habitual <code>notifyDataSetChanged()</code>.</p>
  <pre><span class="lang-tag">Kotlin</span><code><span class="kw">fun</span> <span class="fn">submitList</span>(newList: List&lt;<span class="cl">PokemonDetail</span>&gt;) {
    <span class="kw">val</span> diff = DiffUtil.<span class="fn">calculateDiff</span>(<span class="cl">PokemonDiffCallback</span>(pokemons, newList))
    pokemons = newList
    diff.<span class="fn">dispatchUpdatesTo</span>(<span class="kw">this</span>)  <span class="cm">// Solo redibuja los ítems que cambiaron</span>
}</code></pre>
  <div class="table-wrap">
    <table>
      <thead><tr><th>Método</th><th>Comportamiento</th><th>Eficiencia</th></tr></thead>
      <tbody>
        <tr><td><code>notifyDataSetChanged()</code></td><td>Redibuja todos los ítems siempre</td><td>❌ Ineficiente</td></tr>
        <tr><td><code>DiffUtil</code></td><td>Calcula exactamente qué cambió y anima solo esos ítems</td><td>✅ Óptimo</td></tr>
      </tbody>
    </table>
  </div>
  <p>El <strong>ViewHolder</strong> vincula cada Pokémon a sus vistas y usa Glide para descargar y cachear la imagen de forma asíncrona:</p>
  <pre><span class="lang-tag">Kotlin</span><code><span class="kw">fun</span> <span class="fn">bind</span>(pokemon: <span class="cl">PokemonDetail</span>) {
    binding.tvName.text  = <span class="st">"#${pokemon.id}  ${pokemon.</span><span class="fn">getDisplayName</span><span class="st">()}"</span>
    binding.tvTypes.text = pokemon.<span class="fn">getTypeNames</span>()
    binding.tvHp.text    = <span class="st">"❤️ HP ${pokemon.</span><span class="fn">getHp</span><span class="st">()}"</span>

    Glide.<span class="fn">with</span>(binding.ivSprite.context)
        .<span class="fn">load</span>(pokemon.<span class="fn">getImageUrl</span>())
        .<span class="fn">into</span>(binding.ivSprite)
}</code></pre>
</section>

<section>
  <div class="section-label">Archivo 6 / 6</div>
  <h2>Activity — <code>ui/MainActivity.kt</code></h2>
  <p>La Activity es intencionalmente simple: <strong>solo observa y reacciona</strong>. Toda la lógica vive en el ViewModel.</p>
  <pre><span class="lang-tag">Kotlin</span><code><span class="kw">private fun</span> <span class="fn">observeViewModel</span>() {
    viewModel.uiState.<span class="fn">observe</span>(<span class="kw">this</span>) { state -&gt;
        <span class="kw">when</span> (state) {
            <span class="kw">is</span> <span class="cl">UiState.Loading</span> -&gt; <span class="fn">showLoading</span>()
            <span class="kw">is</span> <span class="cl">UiState.Success</span> -&gt; <span class="fn">showContent</span>(state)
            <span class="kw">is</span> <span class="cl">UiState.Error</span>   -&gt; <span class="fn">showError</span>(state.message)
        }
    }
}</code></pre>
  <div class="callout">
    <div class="callout-icon">🛡️</div>
    <p><code>observe(this, ...)</code> vincula el observador al ciclo de vida de la Activity. Se pausa en background y se elimina al destruirse. <strong>Cero memory leaks.</strong></p>
  </div>
</section>

<hr />

<section>
  <div class="section-label">Arquitectura</div>
  <h2>Flujo completo de datos</h2>
  <div class="flow">
    <div class="flow-step"><div class="flow-num">1</div><div><div class="flow-title">Usuario abre la app</div><div class="flow-desc"><code>MainActivity.onCreate()</code> llama a <code>observeViewModel()</code> y empieza a escuchar cambios.</div></div></div>
    <div class="flow-step"><div class="flow-num">2</div><div><div class="flow-title">ViewModel se inicializa</div><div class="flow-desc"><code>init { fetchPokemons() }</code> dispara la carga. Estado → <code>Loading</code>. La Activity muestra el ProgressBar.</div></div></div>
    <div class="flow-step"><div class="flow-num">3</div><div><div class="flow-title">Retrofit llama a la API</div><div class="flow-desc"><code>GET /pokemon?limit=20</code> devuelve la lista. Se lanzan 20 llamadas <code>GET /pokemon/{id}</code> en paralelo.</div></div></div>
    <div class="flow-step"><div class="flow-num parallel">∥</div><div><div class="flow-title">awaitAll() recoge los resultados</div><div class="flow-desc">Cuando la última llamada termina se tienen los 20 <code>PokemonDetail</code>. Estado → <code>Success</code>.</div></div></div>
    <div class="flow-step"><div class="flow-num">4</div><div><div class="flow-title">Activity renderiza la lista</div><div class="flow-desc"><code>adapter.submitList()</code> → DiffUtil → <code>ViewHolder.bind()</code> × 20 → Glide carga imágenes.</div></div></div>
    <div class="flow-step"><div class="flow-num">!</div><div><div class="flow-title">Error de red</div><div class="flow-desc">Estado → <code>Error</code>. La Activity muestra el mensaje y el botón de reintentar.</div></div></div>
  </div>
</section>

<section>
  <div class="section-label">Fuente de datos</div>
  <h2>API utilizada — PokéAPI</h2>
  <p>Base URL: <code>https://pokeapi.co/api/v2/</code></p>
  <div style="margin: 16px 0;">
    <div class="api-row">
      <div class="api-method">GET</div>
      <div><div class="api-endpoint">/pokemon?limit=20&amp;offset=0</div><div class="api-desc">Lista paginada — devuelve nombre y URL de cada Pokémon</div></div>
    </div>
    <div class="api-row">
      <div class="api-method">GET</div>
      <div><div class="api-endpoint">/pokemon/{id}</div><div class="api-desc">Detalle completo — tipos, stats, sprites de alta resolución</div></div>
    </div>
  </div>
  <div class="checkmarks">
    <span>Completamente gratuita</span>
    <span>Sin registro ni API key requerida</span>
    <span>HTTPS en todos los endpoints</span>
    <span>Activa y mantenida desde 2014</span>
    <span>Más de 500 millones de peticiones al mes</span>
  </div>
</section>

</body>
</html>
