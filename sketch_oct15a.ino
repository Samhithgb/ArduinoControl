int ledPin=13;
int state=0;
int data=0;
 
void setup()
{
  pinMode(ledPin,OUTPUT);
  digitalWrite(ledPin,state);
  Serial.begin(9600);
}
 
void loop()
{
  Serial.println(data);
  if(Serial.available()>0)
  {
    state=!state;
    data=Serial.read();
    switch(data)
    {
      case 177:
        digitalWrite(ledPin,state);
      break;
    }
  }
}
