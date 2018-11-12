<?php	
	$hotelname = $_GET["hotelname"];
	include_once 'config.php';
	$con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
	mysqli_set_charset($con,"utf8");

	if ($queryforhid = mysqli_query($con,"select `h_id` from hotel where `name` ='$hotelname'")){	  
	$hotelrow = $queryforhid->fetch_all(MYSQLI_ASSOC);

 	$h_id =  $hotelrow[0]['h_id'];

}
	if ($result = mysqli_query($con,"select * from `tariff` where `h_id` = '$h_id'")) {
 
//Copy result into a associative array
$Array = $result->fetch_all(MYSQLI_ASSOC);
$resultArray = array('roomList'=>$Array);
echo json_encode($resultArray);
}

?>
