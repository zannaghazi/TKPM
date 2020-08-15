import React, { useState } from 'react';
import { Form, FormControl, Button } from 'react-bootstrap';
import MyComboBoxSearch from './MyComboBoxSearch'
import styles from './static/styles.module.css'
import { useHistory } from "react-router-dom";

function SearchComponent(props) {
    const history = useHistory();
    const [selectValue, setSelectValue] = useState(0)
    const [query, setQuery] = useState("");

    function search(event) {
        let type = "name";
        switch (selectValue) {
            case "0": type = "name";
                break;
            case "1": type = "ISBN";
                break;
            case "2": type = "author";
                break;
            case "3": type = "publisher";
                break;
            default: type = "name";
                break;
        }
        history.push("/list_request?type="+type+"&key="+query);
    }

    function handleChange(event) {
        setQuery(event.target.value);
    }

    return (
        <div className={[styles.mySearchComponent].join(" ")}>
            <Form inline>
                <MyComboBoxSearch setSelectValue={setSelectValue} />
                <FormControl type="text" placeholder="Tìm kiếm" className={["mr-sm-2", styles.myQueryInput].join(" ")} onChange={handleChange} />
                <Button variant="success" className={styles.myButtonSearch} onClick={search}>Tìm kiếm</Button>
            </Form>
        </div>
    );
}

export default SearchComponent;