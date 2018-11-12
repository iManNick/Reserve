<?php	
	$city = $_GET["city"];
	include_once 'config.php';
	$con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
	mysqli_set_charset($con,"utf8");

	if ($result = mysqli_query($con,"select * from `hotel` where `city` = '$city'")) {
 
$Array = $result->fetch_all(MYSQLI_ASSOC);
$resultArray = array('hotelList'=>$Array);
echo json_encode($resultArray);
}

?>
