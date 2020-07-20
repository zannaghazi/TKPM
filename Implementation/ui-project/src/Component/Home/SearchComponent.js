import React from 'react';
import { Form, FormControl, Button } from 'react-bootstrap';
import MyComboBoxSearch from './MyComboBoxSearch'
import styles from './static/styles.module.css'

function SearchComponent(props) {
    return (
        <div className={[styles.mySearchComponent].join(" ")}>
            <Form inline>
                <MyComboBoxSearch />
                <FormControl type="text" placeholder="Tìm kiếm" className={["mr-sm-2", styles.myQueryInput].join(" ")} />
                <Button variant="success" className={styles.myButtonSearch}>Tìm kiếm</Button>
            </Form>
        </div>
    );
}

export default SearchComponent;