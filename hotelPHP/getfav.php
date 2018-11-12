<?php	
	$username = $_GET["username"];
	include_once 'config.php';
	$con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
	mysqli_set_charset($con,"utf8");

	if ($result = mysqli_query($con,"select * from `hotel` h inner join `userfav` uf ON uf.h_id = h.h_id where `username` = '$username' ")) {
 
//Copy result into a associative array
$Array = $result->fetch_all(MYSQLI_ASSOC);
$resultArray = array('hotelList'=>$Array);
echo json_encode($resultArray);
}

?>