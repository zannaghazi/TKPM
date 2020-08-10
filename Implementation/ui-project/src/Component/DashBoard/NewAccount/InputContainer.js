import React from 'react';
import {Row, Col} from 'react-bootstrap';
import styles from '../static/styles.module.css';
import { useState, useEffect } from 'react';

function InputContainer(props) {
    const [type, setType] = useState("text");

    useEffect(() => {
        if(props.is_password){
            setType("password")
        }
      }, []);
    
    return (
        <Row>
            <Col xs={2} className={["d-flex justify-content-end align-items-center", styles.myLabel].join(" ")}>
                {props.label}
                </Col>
            <Col xs={6}>
                <input type={type} className={styles.myInput} />
            </Col>
        </Row>
    );
}

export default InputContainer;