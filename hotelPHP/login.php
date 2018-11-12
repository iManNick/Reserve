<?php
    
    require_once 'user.php';
    
    $username = "";
    
    $password = "";
    
    $email = "";
    
    $phone = "";

    if(isset($_POST['username'])){
        
        $username = $_POST['username'];
        
    }
    
    if(isset($_POST['password'])){
        
        $password = $_POST['password'];
        
    }
    
    if(isset($_POST['email'])){
        
        $email = $_POST['email'];
        
    }
    
    if(isset($_POST['phone'])){
        
        $phone = $_POST['phone'];
        
    }
    
    $userObject = new User();
    
    // Registration
    
    if(!empty($username) && !empty($password) && !empty($email) && !empty($phone)){
        
        
        $json_registration = $userObject->createNewRegisterUser($username, $password, $email, $phone);
        
        echo json_encode($json_registration);
        
    }
    
    // Login
    
    if(!empty($username) && !empty($password) && empty($email) && empty($phone)){
        
        
        $json_array = $userObject->loginUsers($username, $password);
        
        echo json_encode($json_array);
    }
    ?>