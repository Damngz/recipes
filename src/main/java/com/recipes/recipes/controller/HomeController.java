package com.recipes.recipes.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.recipes.recipes.model.Recipe;
import com.recipes.recipes.security.TokenStore;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
public class HomeController {
  private TokenStore tokenStore;

  public HomeController(TokenStore tokenStore) {
    super();
    this.tokenStore = tokenStore;
  }

  private final List<Recipe> recipes = Arrays.asList(
    new Recipe((long) 1, "Ragout de ternera", "https://www.cocinatis.com/archivos/202405/ragout-de-ternera-749x388x80xX.jpg", 120, 7, 2, "1 kilo decarne de ternera para guiso;500ml de agua; 250ml de vino tinto;100gr de tomate frito;200gr de champiñones;1 cebolla;3 zanahorias;3 dientes de ajo;1 hoja de laurel;4 cucharadas de aceite de oliva virgen extra;pimienta negra molida; sal", "Si estás buscando un plato de cuchara que se cocine lentamente, sin prisas, y que ofrezca un sabor profundo y reconfortante, no busques más: el ragout de ternera es exactamente lo que necesitas.\n El ragout es un tipo de rico estofado francés hecho con carne y verduras. Se cocina a fuego lento con vino tinto y hierbas aromáticas, para hacer un plato abundante y lleno de sabor. Hay infinitas variaciones que puedes hacer, con carne, sin ella, pescado, pollo, y puedes usar las verduras o hierbas aromáticas que prefieras, ¡o las que estén en temporada!\n El ragout de ternera es un plato fantástico para hacer cuando el clima comienza a volverse frío. Es un delicioso plato que puedes adaptar al gusto de tu familia y es una excelente receta que puedes preparar con anticipación y recalentar cuando te apetezca.", "Para preparar un delicioso ragout de ternera con carne tierna y jugosa, comienza sazonando con sal y pimienta recién molida 1 kg de carne de ternera para guisar, cortada en trozos del tamaño de un bocado. Esto asegurará una cocción uniforme y una experiencia más cómoda al comer.\n Ahora, en una cazuela, calienta 4 cucharadas de aceite de oliva virgen extra. Cuando el aceite esté caliente, añade los trozos de ternera. Fríe la carne a fuego alto y remueve constantemente para evitar que se pegue, sellándola adecuadamente. Esto preservará los jugos en el interior y garantizará una carne más jugosa y sabrosa.\n Retira la carne de la cazuela y resérvala mientras preparas el sofrito. Para ello, corta 3 dientes de ajo y una cebolla en trozos muy pequeños para facilitar su caramelización y darle más sabor al sofrito. Aprovecha los jugos y el aceite de la carne para sofreír primero ligeramente el ajo y luego añadir la cebolla. Agrega un poco de sal y cocina a fuego medio hasta que la cebolla comience a estar transparente.\n Pela y corta en trozos de 1 cm de grosor aproximadamente 3 zanahorias, luego agrégalas al sofrito junto con 100 g de tomate frito casero. Remueve bien todos los ingredientes y cocínalos juntos durante 5 minutos.\n Una vez listo el sofrito incorporamos la carne y los jugos que ha soltado junto con 1 hoja de laurel.\n Ahora, vierte 250 ml de vino tinto y 500 ml de agua para cubrir la carne. Añade un poco de sal. Tapa la cazuela y deja que la carne comience a cocinarse a fuego medio durante 30 minutos.\n Transcurridos 30 minutos de cocción, incorpora los champiñones al guiso. Limpia y corta 200 g de champiñones por la mitad, añadiéndolos a la cazuela. Luego, tapa la cazuela dejando una pequeña abertura. Cocina el ragout a fuego lento durante 1 hora y 30 minutos, hasta que la carne esté tierna. Remueve ocasionalmente para garantizar una cocción uniforme de la carne.\n Y solo queda disfrutar de este sabroso guiso. Una buena forma de acompañar el ragout de ternera es con puré de patatas, patatas fritas o arroz blanco, sin olvidar una buena hogaza de pan para sopar en la salsa.", "Francia", "https://www.youtube.com/watch?v=ljSH1eYc2WQ"),
    new Recipe((long) 2, "Rösti de patata", "https://www.cocinatis.com/archivos/202207/CTIS0192-Rosti-de-patata-xl_desktop_horizontal_full_width.jpg", 15, 3, 4, "2 patatas;1 cebolla;1 pizca de sal;1 cucharadita de eneldo;1 cucharada de aceite de oliva;pimienta negra molida", "Rösti de patata, receta de Suiza que se sirve como acompañamiento de carnes y verduras.", "Comienza pelando la cebolla y córtandola en juliana fina.\n A continuación, pela las patatas y rállalas. Ponlas en un bol.\n Después, añade la sal, la pimienta negra, el eneldo y la cebolla enjuliana fina. Mezcla todo bien.\n Pon un poco de aceite en una sartén al fuego. Cuando esté caliente, añade a la sartén una porción de patata y cebolla (unas 3 cucharadas) y aplástala para darle forma de torta. Fríe el rosti hasta que esté dorado y dale la vuelta para cocinarlo por los dos lados. Retira de la sartén y déjalo reposar unos minutos en un plato con papel absorbente para retirar el exceso de aceite. Cocina todos los rostis de la misma manera.\n Sirve los rostis de patata calientes. Puedes acompañarlos con salsas de tu gusto o servirlos como guarnición de otros platos. ¡A disfrutar de esta receta de Suiza!", "Suiza","https://www.youtube.com/watch?v=siNe1sc3YiU"),
    new Recipe((long) 3, "Cerdo agridulce", "https://www.cocinatis.com/archivos/202207/CTIS0237-receta-cerdo-agridulce_desktop_horizontal_full_width.jpg", 45, 6, 4, "500gr de lomo de cerdo;1 pimiento rojo;1 pimiento verde;1 cebolleta;aceite de oliva virgen extra;1 diente de ajo;jengibre;azúcar;4 cucharadas de salsa de soja;1/2 vaso de vinagre de arroz;1 cucharada de maicena;1 cucharada de aceite de sésamo;60gr de harina de trigo;30gr de maicena;2 huevos;1/2 cucharadita de levadura;3 cucharadas de agua;3 cucharadas de ketchup;1 cucharada de vinagre de arroz;1/2 cucharadita de salsa inglesa;5 cucharadas de agua", "Aprende a elaborar de forma sencilla esta receta de cerdo agridulce, un plato tradicional chino.", "Corta el lomo de cerdo en dados.\n Para el marinado: Añade en un recipiente la harina de maíz, el vinagre de arroz, la salsa de soja y el aceite se sésamo. Remueve con unas varillas. Añade los dados de cerdo al marinado y tapa con papel film. Deja marinar el cerdo unas 3 horas en el frigorífico.\n Para la salsa: Pon a calentar en una sartén con aceite de oliva el ajo, añade la cebolleta, sal y remueve con una cuchara. Añade el pimiento verde, y el pimiento rojo troceado, remueve. Añade el jengibre, azúcar, ketchup, la salsa inglesa, el agua y el vinagre de arroz. Remueve. Baja la temperatura a fuego suave y deja que reduzca.\n Para el rebozado: Pon en un plato hondo la harina, el almidón de maíz, la levadura, los huevos y el agua. Mezcla bien hasta tener una masa homogénea sin grumos.\n Pon a calentar al fuego una cazuela baja con abundante aceite. Pasa los dados de cerdo marinado por el rebozado y fríe. Cuando estén dorados por un lado, dales la vuelta y fríe por el otro lado.\n Saca del fuego y ponlos en un plato con papel absorbente para quitar el exceso de grasa. Añade el cerdo rebozado a la sartén de la salsa y remueve.\n Sirve el cerdo rebozado acompañado de la salsa.", "China","https://www.youtube.com/watch?v=r1U79ZHR-as"),
    new Recipe((long) 4, "Pasteles de carne", "https://www.cocinatis.com/archivos/202207/CTIS0387-Pasteles-de-carne-ingleses_desktop_horizontal_full_width.jpg", 90, 4, 9, "1 lámina de masa quebrada;250gr de carne picada;1/2 cebolla;1/2 manzana;2 huevos;40gr de mostaza;nuez moscada", "Los pasteles de carne son muy populares en Reino Unido. En esta ocasión, los hemos rellenado con ternera, huevo, manzana, nuez moscada y mostaza.", "Pica la cebolla y el huevo duro. Ralla la manzana.\n Pon una sartén en el fuego con un chorro de aceite de oliva, añade la cebolla picada, la sal y saltea. Deja pochar bien la cebolla a fuego suave durante una media hora.\n En un bol grande añade la cebolla pochada, la carne picada, la manzana rallada, el huevo duro picado, la mostaza, sal, pimienta y nuez moscada rallada. Mezcla todo bien con una cuchara.\n Extiende la lámina de pasta brisa. Para cada pastel cortaremos dos círculos de masa, uno de base más grande usando un vaso boca abajo y otro más pequeño de tapa usando un cortapastas.\n En una bandeja de horno con moldes coloca en cada hueco una base de círculo de pasta pegada a la pared del molde. Sobre cada molde con la mezcla de carne picada. Tapa cada pastel con los círculos de pasta más pequeños cubriendo toda la superficie. Pinta cada pastel con huevo batido.\n Introduce la bandeja en el horno precalentado a 200ºC durante unos 30 minutos hasta que estén bien dorados.\n Sirve los pasteles de carne ingleses.", "Inglaterra","https://youtube.com/watch?v=uyBsUzDWbbo"),
    new Recipe((long) 5, "Carne a la cubana", "https://www.cocinatis.com/archivos/202207/CTIS0096-Carne-cubana-xl2_desktop_horizontal_full_width.jpg", 30, 3, 2, "250gr de carne picada de cerdo;250gr de carne picada de ternera;1 cebolla;1 pimiento verde;1 huevo;12 aceitunas negras;1/2 vaso de caldo de carne;1/2 vaso de vino blanco;1 pizca de sal;1 cucharadita de comino;pimienta;1 chorrito de aceite de oliva", "Delicioso plato de carne a la cubana acompañado de arroz blanco.", "Pela la cebolla y pícala. Corta el pimiento en dados y las aceitunas negras en rodajas.\n Pon en un bol el huevo y bate. Añade la sal, el comino y la pimienta. Agrega la carne picada de ternera y la carne picada de cerdo. Mezcla todo bien con las varillas.\n Pon un poco de aceite en una cazuela baja al fuego. Añade la cebolla, el pimiento, una pizca de sal, pimienta. Rehoga a fuego fuerte durante medio minuto. Remueve y agrega la carne. Mezcla bien hasta que quede todo incorporado.\n Cuando esté casi cocinada la carne, incorpora el caldo de carne y el vino blanco. Remueve y cocina hasta que se reduzca el caldo.\n Incorpora las aceitunas negra cortadas en rodajas.\n Sirve la carne a la cubana y acompaña con arroz blanco.", "Cuba","https://www.youtube.com/watch?v=GkYdY1fcOcs"),
    new Recipe((long) 6, "Patatas rellenas", "https://www.cocinatis.com/archivos/202405/receta-de-patatas-asadas-1-749x388x80xX.jpg", 80, 3, 4, "4 patatas;1/4 col;1 manzana granny smith;2 zanahorias;2 cucharadas de mayonesa;perejil", "Las patatas rellenas o Jacket potatoes son uno de esos platos que siempre triunfan. Seguro que has ido alguna vez a la feria y has visto una larga cola de gente que está esperando a comprar en un puesto de patatas asadas, y es que es un plato versátil, fácil de comer y que gusta a todo el mundo.\n Un clásico de la cocina inglesa que puedes preparar al horno o envuelto con papel albal en las brasas de un asado. Un plato fácil de preparar y que puede ser una alternativa estupenda si buscas preparar un plato completo y del que podrás disfrutar con tus ingredientes favoritos.\n Sigue los pasos de nuestra receta y aprende a cómo hacer tus propias patatas rellenas al horno en casa, nosotros las rellenamos con una ensaladilla casera, pero podrás disfrutar de una patata rellena con los ingredientes que más te gusten. ¡No te lo puedes perder!", "Darás comienzo a la receta de patatas rellenas al horno asando las patatas, para ello envuelve las patatas en papel de aluminio y ponlas en la bandeja del horno durante 1 hora más o menos a 220ºC. Es importante que las pinches antes con ayuda de un palillo o un tenedor. Puede que necesites más de una hora, depende del horno y de la patata.\n Comprueba la resistencia de la patata cuando creas que esté lista con ayuda de un cuchillo de untar.\n Para preparar el relleno: pica la col, las zanahorias y la manzana. Pon todo en un bol, añade la mahonesa y mezcla bien.\n Saca las patatas del horno y deja enfriar unos minutos. Hazles un corte en la mitad (sin llegar a partirlas en dos).\n Rellena la patata con la ensalada de col. Espolvorea con perejil picado para decorar. Sirve las patatas rellenas mientras estén calientes y disfrútalas a tu gusto.", "Inglaterra","https://www.youtube.com/watch?v=XipuAHIfdXg")
  );

  @GetMapping("/")
  public String root(Model model) {
    model.addAttribute("recipes", recipes);
    return "home";
  }

  @GetMapping("/recetas")
  public String home(Model model) {
    model.addAttribute("recipes", recipes);
    return "home";
  }

  @GetMapping("/greetings")
  public String getMethodName(@RequestParam(name="name", required=false, defaultValue = "Grupo 14") String name, Model model) {
    String url = "http://localhost:8081/greetings";

    final var restTemplate = new RestTemplate();
    String token = tokenStore.getToken();

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", token);

    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("name", name);

    ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

    model.addAttribute("name", response.getBody());
    return "greetings";
  }
  
}
