import React, { useState, useEffect } from 'react';
import styles from './static/styles.module.css'
import { useHistory } from "react-router-dom";
import config from '../../asset/config.json'

function FormLogin(props) {
    const [userName, setUserName] = useState("");
    const [passWord, setPassWord] = useState("");
    const [error, setError] = useState("");
    const history = useHistory();

    function handleSubmit(event){
        let url= config.severAPi.hostUrl + ":8083/system/login";
        event.preventDefault();
        fetch(url, {
            method: "post",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({username: userName, password: passWord})
        })
            .then(res => res.json())
            .then(data => {
                console.log("login",data);
                if(data.token){
                    localStorage.quanlythuvien_accesstoken = data.token;
                    delete data.token;
                    props.onSetUserLogin(data);
                    history.push("/");
                }
                else{
                    setError("Sai thông tin đăng nhập !")
                }
            });
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
            <div className= {styles.myErrMessage}>{ error }</div>
            <input type="submit" className="fadeIn fourth" value="Đăng nhập" />
        </form>
    );
}

export default FormLogin;