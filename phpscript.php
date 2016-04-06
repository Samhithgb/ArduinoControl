<?php
error_reporting(E_ALL);
ini_set("display_errors",'1');
require_once ("php_serial.class.php"); // include the php serial class
$data=(int)$_POST["message"]; // get the message from the phone
$serial = new phpSerial();// create a serial object
 // specify where you want to send the data to (where the arduino is connected in the usb ports)
$serial->deviceSet("/dev/ttyACM0");
$serial->confBaudRate(9600);// the transfer data rate of your arduino
$serial->deviceOpen();// start the communication
$serial->sendMessage($data); // send message to arduino
$serial->deviceClose();// close the serial connection
?>


