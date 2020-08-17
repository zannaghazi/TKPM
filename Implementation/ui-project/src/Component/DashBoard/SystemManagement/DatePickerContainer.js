import React, {useState} from 'react';
import DatePicker from "react-datepicker";
import { Row, Col } from 'react-bootstrap';
import "react-datepicker/dist/react-datepicker.css";
import styles from '../static/styles.module.css';

function DatePickerContainer(props) {
    const [dob, setDob] = useState(new Date());

    function handleChange(event) {
        setDob(event.target.value);
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
                />
            </Col>
        </Row>
    );
}

export default DatePickerContainer;