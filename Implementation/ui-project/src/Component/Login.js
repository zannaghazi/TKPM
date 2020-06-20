import React from 'react';
import styles from './static/styles.module.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import './static/my-login.css'
import FormLogin from './FormLogin'

function Login(props) {
    console.log(styles);
    return (
        <div className={styles.myLoginPage}>
            <div class="fadeInDown my-login">
                <div id="formContent" method="post" novalidate name="formContent">
                    <div class="fadeIn first">
                        <label class="my-label">Chào mừng quý khách đến với ZanLib</label>
                    </div>
                    <FormLogin />
                    <div id="formFooter">
                        <a class="underlineHover my-login-link" href="#">Quên mật khẩu</a>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;