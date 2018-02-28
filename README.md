# Cel Projektowy

Program został stworzony jako projekt w ramach przedmiotu "Algorytmy i struktury danych" na Politechnice Warszawskiej.

Jednym z zadań w ramach tego przedmiotu było stworzenie programu walczącego, sztucznej inteligencji, która miała konkrurować z innymi, stworzonymi przez kolegów z grupy dziełami w ramach jakiejś wcześniej obranej gry.
## Zasady gry

1) Plansza na której toczy się rozgrywka to kwadrat n*n o n będącym liczbą nieparzystą niewiększą od 999.
2) Plansza to zbiór komórek które mają być później zapełnione przez graczy pdczas kolejnych ruchów.
3) Nie wszystkie komórki muszą być wstępnie puste. Program Sędziowski może (na życzenie jego Operatora) planszę niektóre komórki wypełnić.
<li>    
        Przykładowy wygląd planszy startowej:

     .01234. 
    0|X X X|
    1|  X  |
    2| X   |
    3|   X |
    4|_____|
       

4) Gracze (algorytmy walczące) rozstawiają na przemian prostokąty o wymiarach 2x1 pionowo lub poziomo na planszy.
5) Rozgrywka kończy się gdy nie da się już postawić żadnego nowego prostokąta.

## Protokół komunikacji Gracz-Sędzia

1) Sędzia komunikuje się z Graczami za pomocą ich standardowych wejść i wyjść ( stdin , stdout ).
<ol>
<li>
        <b>Sędzia</b> przesyła pierwszemu <b>graczowi</b> informację o rozmiarze 
        <b>planszy</b>, a także o <b>komórkach</b> już zapełnionych przez system.
        np. <b>Plansza</b> rozmiaru 7x7 o zapełnionych <b>komórkach</b>
        o indeksach (2,3) i (4,5).
        
    7_2x3_4x5        
<li>
        <b>Gracz</b> przesyła informację zwrotną o przyjęciu komunikatu. Ma na to 1s.
        
    OK        
<li>
        <b>Sędzia</b> przesyła drugiemu <b>graczowi</b> tą samą informację.
        
    7_2x3_4x5
<li>
        Drugi <b>gracz</b> ma również sekundę na odpowiedź.
        
    OK        
<li>
        <b>Sędzia</b> przesyła pierwszemu <b>graczowi</b> informacje o rozpoczęciu gry.
        
    START
<li>
        <b>Gracz</b> ma 0.5s na odpowiedź jaki ruch ( wstawienie prostokąta 2x1 ) zamierza wykonać.
        np. wstawienie na <b>plaszę</b> prostokąta na <b>komórki</b> (1,2) , (2,2).
          
    1x2_2x2
<li>
        <b>Sędzia</b> wysyła drugiemu <b>graczowi</b> informację o ruchu poprzedniego.
        
    1x2_2x2
<li>
        <b>Gracz</b> ma 0.5s na wykonanie swojego ruchu.
       
    3x0_3x1
<li>
        <b>Sędzia</b> przesyła pierwszemu <b>graczowi</b> informację o ruchu poprzedniego.
        
    3x0_3x1
<li>
        itd. aż któryś z graczy spełni <b>warunki przegranej</b>.
        				
</ol>

## Warunki pregranej


<ol>
<li>
        Przekroczenie dozwolonego czasu przez <b>gracza</b> powoduje
        jego automatyczną przegraną.
<li>
        <b>Gracz</b>, który wykonał ruch nieprzewidziany w zasadach
        automatycznie przegrawa.
<li>
        Jeżeli gra zakończyła się w sposób przewidziany w <b>Regulaminie</b>,
        przegrywa gracz, który miałby wykonywać następny ruch (<i>czyli ten,
        który nie może już wykonać ruchu</i>).
</ol>

<br> 
Po zakończonej rozgrywce <b>Sędzia</b> wysyła: 
    
    STOP
    
do wszystkich <b>graczy</b> (<i><b>Program Gracz</b> powinien się zakończyć,
ale <b>Sędzia</b> profilaktycznie dodatkowo zabija proces.</i>).
