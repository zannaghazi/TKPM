import React from 'react';
import MyLogo from '../../../asset/logo/logo.png'
import styles from './static/styles.module.css'

function Logo(props) {
    return (
        <div >
            <a href="/">
                <img className={styles.myLogo}  src={MyLogo} alt="Online Auction logo" />
            </a>
        </div>
    );
}

export default Logo;