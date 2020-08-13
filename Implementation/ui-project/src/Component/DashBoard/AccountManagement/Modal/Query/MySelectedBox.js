import React from 'react';
import styles from '../static/styles.module.css'

function MySelectedBox(props) {
    function handleChange(event){
        props.byValue(event.target.value);
    }

    return (
        <div className={styles.box}>
            <select className={styles.mySelect} onChange={handleChange}>
                <option value="0">Tên đăng nhập</option>
                <option value="1">Tên riêng</option>
            </select>
        </div>
    );
}

export default MySelectedBox;