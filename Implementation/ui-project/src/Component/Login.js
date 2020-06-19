import React from 'react';
import styles from './static/styles.module.css'

function Login(props) {
    console.log(styles);
    return (
        <div className={styles.myLoginPage}>
            <h1 className={styles.myFont}>aaaa</h1>
        </div>
    );
}

export default Login;