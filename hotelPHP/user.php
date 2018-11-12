<?php
    
    include_once 'db-connect.php';
    
    class User{
        
        private $db;
        
        private $db_table = "users";
        
        public function __construct(){
            $this->db = new DbConnect();
        }
        
        public function isLoginExist($username, $password){
            
            $query = "select * from ".$this->db_table." where username = '$username' AND password = '$password' Limit 1";
            
            $result = mysqli_query($this->db->getDb(), $query);
            
            if(mysqli_num_rows($result) > 0){
                
                mysqli_close($this->db->getDb());
                
                
                return true;
                
            }
            
            mysqli_close($this->db->getDb());
            
            return false;
            
        }
        
        public function isEmailUsernameExist($username, $email){
            
            $query = "select * from ".$this->db_table." where username = '$username' AND email = '$email'";
            
            $result = mysqli_query($this->db->getDb(), $query);
            
            if(mysqli_num_rows($result) > 0){
                
                mysqli_close($this->db->getDb());
                
                return true;
                
            }
               
            return false;
            
        }
        
        public function isValidEmail($email){
            return filter_var($email, FILTER_VALIDATE_EMAIL) !== false;
        }
        
        public function createNewRegisterUser($username, $password, $email, $phone){
              
            $isExisting = $this->isEmailUsernameExist($username, $email);
            
            if($isExisting){
                
                $json['success'] = 0;
                $json['message'] = "خطا در ثبت نام! احتمالا نام کاربری یا ایمیل وارد شده قبلا ثبت شده است";
            }
            
            else{
                
            $isValid = $this->isValidEmail($email);
                
                if($isValid)
                {
                $query = "insert into ".$this->db_table." (username, password, email, phone) values ('$username', '$password', '$email','$phone')";
                
                $inserted = mysqli_query($this->db->getDb(), $query);
                
                if($inserted == 1){
                    
                    $json['success'] = 1;
                    $json['message'] = "ثبت نام با موفق انجام شد";
                    
                }else{
                    
                    $json['success'] = 0;
                    $json['message'] = "خطا در ثبت نام! احتمالا نام کاربری یا ایمیل وارد شده قبلا ثبت شده است";
                    
                }
                
                mysqli_close($this->db->getDb());
                }
                else{
                    $json['success'] = 0;
                    $json['message'] = "خطا در ثبت نام! فرمت ایمیل وارد شده نادرست است";
                }
                
            }
            
            return $json;
            
        }
        
        public function loginUsers($username, $password){
            
            $json = array();
            
            $canUserLogin = $this->isLoginExist($username, $password);
            
            if($canUserLogin){
                
                $json['success'] = 1;
                $json['message'] = "با موفقیت وارد شدید";
                
            }else{
                $json['success'] = 0;
                $json['message'] = "اطلاعات نادرست";
            }
            return $json;
        }
    }
    ?>