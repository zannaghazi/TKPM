import React from 'react';
import Logo from './Logo';
import SearchComponent from './SearchComponent'
import {Container, Col, Row} from 'react-bootstrap'

function Header(props) {
    return (
        <Container>
            <Row>
                <Col xs={2}>
                    <Logo />
                </Col>
                <Col xs={2}>
                    
                </Col>
                <Col xs={8}>
                    <SearchComponent />
                </Col>
            </Row>
        </Container>
    );
}

export default Header;