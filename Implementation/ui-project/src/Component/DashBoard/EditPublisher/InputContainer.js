import React from 'react';
import {Row, Col} from 'react-bootstrap';
import styles from '../static/styles.module.css';
import { useState, useEffect } from 'react';

function InputContainer(props) {
    const [value, setValue] = useState("");

    useEffect(() => {
        setValue(props.nameBinding);
        console.log(props.nameBinding)
        
      }, [props.nameBinding]);
    
    function handleChange(event){
        props.byName(event.target.value);
    }

    return (
        <Row>
            <Col xs={2} className={["d-flex justify-content-end align-items-center", styles.myLabel].join(" ")}>
                {props.label}
                </Col>
            <Col xs={6}>
                <input type="text" className={styles.myInput} onChange={handleChange} value = {value}/>
            </Col>
        </Row>
    );
}

export default InputContainer;