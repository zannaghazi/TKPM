import React from 'react';
import styles from '../static/styles.module.css'

function MySelectedBox(props) {
    return (
        <div className={styles.box}>
            <select className={styles.mySelect}>
                <option value="0">Trạng thái</option>
                <option value="1">Mã thư viện</option>
                <option value="1">Tên người mượn</option>
                <option value="1">ID người mượn</option>
            </select>
        </div>
    );
}

export default MySelectedBox;