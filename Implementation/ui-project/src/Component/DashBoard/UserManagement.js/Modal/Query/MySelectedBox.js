import React from 'react';
import styles from '../static/styles.module.css'

function MySelectedBox(props) {
    function handleChange(event){
        props.byValue(event.target.value);
    }

    return (
        <div className={styles.box}>
            <select className={styles.mySelect} onChange={handleChange}>
                <option value="0">Tên sách mượn</option>
                <option value="1">ISBN sách mượn</option>
                <option value="2">Thẻ thư viện</option>
            </select>
        </div>
    );
}

export default MySelectedBox;