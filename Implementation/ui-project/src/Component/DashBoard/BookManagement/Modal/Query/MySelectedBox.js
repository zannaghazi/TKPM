import React from 'react';
import styles from '../static/styles.module.css'

function MySelectedBox(props) {
    function handleChange(event){
        props.byValue(event.target.value);
    }

    return (
        <div className={styles.box}>
            <select className={styles.mySelect} onChange={handleChange}>
                <option value="0">Trạng thái</option>
                <option value="1">Mã thư viện</option>
                <option value="2">Tên người mượn</option>
                <option value="3">ID người mượn</option>
            </select>
        </div>
    );
}

export default MySelectedBox;