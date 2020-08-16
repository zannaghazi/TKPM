import React, {useState, useEffect} from 'react';
import DatePicker from "react-datepicker";
import { Row, Col } from 'react-bootstrap';
import "react-datepicker/dist/react-datepicker.css";
import styles from '../static/styles.module.css';

function DatePickerContainer(props) {
    const [dob, setDob] = useState(new Date());

    useEffect(() => {
        if(props.value){
            setDob(Date.parse(props.value))
        }
    }, [props.value]);

    function handleChange(event) {
        console.log("dob in datepicker", event)
        setDob(event);
        props.byData(event.getDate()+ "-"+(event.getMonth()+1)+"-"+event.getFullYear());
    };

    return (
        <Row>
            <Col xs={2} className={["d-flex justify-content-end align-items-center", styles.myLabel].join(" ")}>
                {props.label}
            </Col>
            <Col xs={6}>
                <DatePicker
                    selected={dob}
                    onChange={handleChange}
                    className={styles.myInput}
                    dateFormat='dd/MM/yyyy'
                />
            </Col>
        </Row>
    );
}

export default DatePickerContainer;