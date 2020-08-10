import React from 'react';
import styles from '../static/styles.module.css'

function MySelectedBox(props) {
    return (
        <div className={styles.box}>
            <select className={styles.mySelect}>
                <option value="0">Tên đăng nhập</option>
                <option value="1">Tên riêng</option>
            </select>
        </div>
    );
}

export default MySelectedBox;