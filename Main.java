//CUSTOMIZABLE BELOW : (ALSO CHECK AGENT CLASS)
  

//COLOURS : 
color rgbA = color(255, 128, 179); //Agent colour
color rgbB = color(0, 179, 143); //Background colour
color rgbP = color(51, 0, 128);  //Colour pheromone will fade to

//AGENTS : 
int numAgents = 25;
float speed = 3;
int size = 5;
int max = 1000;

//NOISE : 
String noise = "perlin";
float noiseScale = 0.004;

//PHEROMONES
float pheroDecay = 0.0175;
float pheroThreshold = 0;

//SPAWN
String orientation = "UP";
//END OF CUSTOMIZABLE

ArrayList<Agent> agents = new ArrayList<Agent>();
ArrayList<Pheromone> pheromones = new ArrayList<Pheromone>();
float shift = 0;

void spawn(){
  if(orientation == "UP"){
    shift = PI/2;
  } else if(orientation == "DOWN"){
    shift = 3*PI/2;
  } else if(orientation == "LEFT"){
    shift = 0;
  } else if(orientation == "RIGHT"){
    shift = PI;
  }

  if((agents.size() < max)){
    int num = (int) random(numAgents);
    for(int i=0; i<num; i++){
      agents.add(new Agent(speed, shift, noise,noiseScale, size, rgbA));
    }
  }
}
void setup()  {
  size(900,900); 
  noStroke();
  rectMode(CENTER);

}

void draw(){
  background(rgbB); 
  //println(agents.size());
  spawn();
  for(int i=0;i<agents.size(); i++){
    pheromones.add(new Pheromone(int(agents.get(i).x), int(agents.get(i).y), pheroDecay, rgbA, rgbP, size));
    agents.get(i).update(shift);
    agents.get(i).show();
  }

  //Clean agents
  for (int j = agents.size() - 1; j >= 0; j--) {
    Agent a  = agents.get(j);
    if (a.toDel) {
      agents.remove(j);
    }
  }
  //Clean pheros
  for (int j = pheromones.size() - 1; j >= 0; j--) {
    Pheromone p = pheromones.get(j);
    p.show();
    p.decay();
    
    if(p.strength <= pheroThreshold) {
      pheromones.remove(j);
    }
  }
  if(frameCount<1200){
    saveFrame("frame-" + nf(frameCount, 4) + ".png");
    println(frameCount);
  } else {
    println("Done");
    exit();
  }
}
void mouseClicked(){
  if(mouseButton != RIGHT){//Left click : increase shift clockwise
    shift = shift + PI/2;
    println("LEFT click");
    println(shift);

  } else {
    println("RIGHT click");
    shift = shift - PI/2;
    println(shift);
  }
}
void keyPressed(){
  if(keyCode == UP){
    noiseScale += 0.002;
  } else if(keyCode == DOWN){
    noiseScale -= 0.002;
  } else if (keyCode == 32){
    println("eyo");
    noiseSeed(millis());
  }
}