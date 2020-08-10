import React, { useState, useEffect } from 'react';
import styles from './static/styles.module.css'
import { useHistory } from "react-router-dom";

function FormLogin(props) {
    const [userName, setUserName] = useState("");
    const [passWord, setPassWord] = useState("");
    const [error, setError] = useState("");
    const history = useHistory();

    function handleSubmit(event){
        event.preventDefault();
        if(userName === "nthoang1996" && passWord === "123456"){
            localStorage.librarymanagement_token = "123456789qwertyuiopasdfghjklzxcvbnm0147852369zaqwsxcderfvbgtyhnmjuik";
            history.push("/");
        }
        else{
            setError("Tài khoản hoặc mật khẩu không chính xác");
        }
    }

    function userNameChange(event){
        setUserName(event.target.value);
    }

    function passwordChange(event){
        setPassWord(event.target.value);
    }

    return (
        <form onSubmit = {handleSubmit}>
            <input
                type="text"
                id="login"
                className="fadeIn second"
                name="login"
                placeholder="Tài khoản"
                value = {userName}
                onChange = {userNameChange}
            />
            <input
                type="password"
                id="password"
                className="fadeIn third"
                name="password"
                placeholder="Mật khẩu"
                value = {passWord}
                onChange = {passwordChange}
            />
            <div v-if="err != ''" className= {styles.myErrMessage}>{ error }</div>
            <input type="submit" className="fadeIn fourth" value="Đăng nhập" />
        </form>
    );
}

export default FormLogin;