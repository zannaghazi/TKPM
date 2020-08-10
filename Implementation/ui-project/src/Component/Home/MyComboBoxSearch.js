import React from 'react';
import styles from './static/styles.module.css'

function MyComboBoxSearch(props) {
    return (
        <div className={styles.box}>
            <select className = {styles.mySelect}>
                <option value="0">Tên sách</option>
                <option value="0">Mã ISBN</option>
                <option value="0">Tên tác giả</option>
                <option value="0">Nhà xuất bản</option>
            </select>
        </div>
        )
}

export default MyComboBoxSearch;