import React from 'react';
import { TouchableOpacity } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { useNavigation } from '@react-navigation/native';

interface MenuButtonProps {
  color?: string;
  size?: number;
}

const MenuButton: React.FC<MenuButtonProps> = ({ 
  color = '#6200ee', 
  size = 24 
}) => {
  const navigation = useNavigation();

  return (
    <TouchableOpacity
      onPress={() => {
        // Try to open drawer using dispatch
        const parent = navigation.getParent();
        if (parent) {
          parent.dispatch({
            type: 'OPEN_DRAWER',
            target: parent.getState().key,
          });
        } else {
          console.log('Menu pressed - drawer not available');
        }
      }}
      style={{ marginLeft: 15 }}
    >
      <Ionicons name="menu" size={size} color={color} />
    </TouchableOpacity>
  );
};

export default MenuButton;
