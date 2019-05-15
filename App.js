/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {requireNativeComponent, Text, View} from 'react-native';

const TMap = requireNativeComponent("TMap")

export default class App extends Component {
  
  render() {
    return (
      <View style={{flex:1}}> 
        <TMap
          style={{flex: 1}}
          setCenter={[127.0, 37.0]}
        />
      </View>
    );
  }
}
