import React from 'react';
import styles from './static/styles.module.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import './static/my-login.css'
import FormLogin from './FormLogin'

function Login(props) {
    console.log(styles);
    return (
        <div className={styles.myLoginPage}>
            <div className="fadeInDown my-login">
                <div id="formContent" method="post" noValidate name="formContent">
                    <div className="fadeIn first">
                        <label className="my-label">Chào mừng quý khách đến với ZanLib</label>
                    </div>
                    <FormLogin />
                    <div id="formFooter">
                        
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;