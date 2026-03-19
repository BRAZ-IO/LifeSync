import React from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  ScrollView,
  Image,
} from 'react-native';
import { DrawerContentComponentProps, DrawerContentScrollView } from '@react-navigation/drawer';
import { useTheme } from 'react-native-paper';
import { Ionicons } from '@expo/vector-icons';
import { CommonActions } from '@react-navigation/native';

interface SidebarProps extends DrawerContentComponentProps {}

const Sidebar: React.FC<SidebarProps> = (props) => {
  const theme = useTheme();

  // Mock user data for demo
  const mockUser = {
    name: 'Usuário Demo',
    email: 'demo@lifesync.com',
  };

  const menuItems = [
    {
      id: 'dashboard',
      title: 'Dashboard',
      icon: 'home-outline',
      onPress: () => props.navigation.navigate('Main', { screen: 'Dashboard' }),
    },
    {
      id: 'workouts',
      title: 'Treinos',
      icon: 'fitness-outline',
      onPress: () => props.navigation.navigate('Main', { screen: 'Workouts' }),
    },
    {
      id: 'habits',
      title: 'Hábitos',
      icon: 'checkmark-circle-outline',
      onPress: () => props.navigation.navigate('Main', { screen: 'Habits' }),
    },
    {
      id: 'progress',
      title: 'Progresso',
      icon: 'stats-chart-outline',
      onPress: () => props.navigation.navigate('Main', { screen: 'Progress' }),
    },
    {
      id: 'nutrition',
      title: 'Nutrição',
      icon: 'restaurant-outline',
      onPress: () => console.log('Nutrition'),
    },
    {
      id: 'ai-coach',
      title: 'Treinador IA',
      icon: 'sparkles-outline',
      onPress: () => console.log('AI Coach'),
    },
  ];

  const secondaryItems = [
    {
      id: 'profile',
      title: 'Perfil',
      icon: 'person-outline',
      onPress: () => props.navigation.navigate('Main', { screen: 'Profile' }),
    },
    {
      id: 'settings',
      title: 'Configurações',
      icon: 'cog-outline',
      onPress: () => console.log('Settings'),
    },
    {
      id: 'help',
      title: 'Ajuda',
      icon: 'help-circle-outline',
      onPress: () => console.log('Help'),
    },
    {
      id: 'about',
      title: 'Sobre',
      icon: 'information-circle-outline',
      onPress: () => console.log('About'),
    },
  ];

  const handleLogout = async () => {
    // Navigate to a simple "logged out" state or just close drawer
    props.navigation.closeDrawer();
  };

  return (
    <View style={[styles.container, { backgroundColor: '#1a1a1a' }]}>
      {/* Header */}
      <View style={[styles.header, { backgroundColor: '#2d2d2d' }]}>
        <TouchableOpacity
          onPress={() => props.navigation.closeDrawer()}
          style={styles.closeButton}
        >
          <Ionicons name="close" size={24} color="#ffffff" />
        </TouchableOpacity>
        
        <View style={styles.profileSection}>
          <Image
            source={{ uri: 'https://via.placeholder.com/80' }}
            style={styles.avatar}
          />
          <Text style={[styles.userName, { color: '#ffffff' }]}>{mockUser.name}</Text>
          <Text style={[styles.userEmail, { color: 'rgba(255, 255, 255, 0.7)' }]}>{mockUser.email}</Text>
          <View style={[styles.levelBadge, { backgroundColor: 'rgba(255, 255, 255, 0.2)' }]}>
            <Ionicons name="trophy" size={16} color="#FFD700" />
            <Text style={[styles.levelText, { color: '#FFD700' }]}>Nível 12</Text>
          </View>
        </View>
      </View>

      {/* Main Menu */}
      <DrawerContentScrollView
        {...props}
        contentContainerStyle={styles.menuContainer}
      >
        <View style={styles.menuSection}>
          <Text style={[styles.sectionTitle, { color: '#888888' }]}>
            Menu Principal
          </Text>
          {menuItems.map((item) => (
            <TouchableOpacity
              key={item.id}
              style={styles.menuItem}
              onPress={item.onPress}
            >
              <Ionicons
                name={item.icon as any}
                size={22}
                color="#6200ee"
              />
              <Text style={[styles.menuItemText, { color: '#ffffff' }]}>
                {item.title}
              </Text>
            </TouchableOpacity>
          ))}
        </View>

        <View style={[styles.divider, { backgroundColor: '#333333' }]} />

        <View style={styles.menuSection}>
          <Text style={[styles.sectionTitle, { color: '#888888' }]}>
            Configurações
          </Text>
          {secondaryItems.map((item) => (
            <TouchableOpacity
              key={item.id}
              style={styles.menuItem}
              onPress={item.onPress}
            >
              <Ionicons
                name={item.icon as any}
                size={22}
                color="#888888"
              />
              <Text style={[styles.menuItemText, { color: '#ffffff' }]}>
                {item.title}
              </Text>
            </TouchableOpacity>
          ))}
        </View>

        {/* Logout */}
        <TouchableOpacity style={styles.logoutItem} onPress={handleLogout}>
          <Ionicons name="log-out-outline" size={22} color="#FF5252" />
          <Text style={[styles.logoutText, { color: '#FF5252' }]}>
            Sair
          </Text>
        </TouchableOpacity>
      </DrawerContentScrollView>

      {/* Footer */}
      <View style={[styles.footer, { borderTopColor: '#333333' }]}>
        <Text style={[styles.footerText, { color: '#888888' }]}>
          LifeSync v1.0.0
        </Text>
        <Text style={[styles.footerText, { color: '#888888' }]}>
          © 2024 LifeSync
        </Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  header: {
    paddingTop: 50,
    paddingBottom: 30,
    paddingHorizontal: 20,
  },
  closeButton: {
    position: 'absolute',
    top: 50,
    right: 20,
    zIndex: 1,
  },
  profileSection: {
    alignItems: 'center',
    marginTop: 10,
  },
  avatar: {
    width: 80,
    height: 80,
    borderRadius: 40,
    marginBottom: 15,
  },
  userName: {
    fontSize: 20,
    fontWeight: 'bold',
    color: 'white',
    marginBottom: 5,
  },
  userEmail: {
    fontSize: 14,
    color: 'rgba(255, 255, 255, 0.8)',
    marginBottom: 10,
  },
  levelBadge: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: 'rgba(255, 255, 255, 0.2)',
    paddingHorizontal: 12,
    paddingVertical: 6,
    borderRadius: 16,
  },
  levelText: {
    color: '#FFD700',
    fontSize: 12,
    fontWeight: 'bold',
    marginLeft: 5,
  },
  menuContainer: {
    flexGrow: 1,
  },
  menuSection: {
    paddingVertical: 10,
  },
  sectionTitle: {
    fontSize: 12,
    fontWeight: 'bold',
    textTransform: 'uppercase',
    paddingHorizontal: 20,
    marginBottom: 10,
  },
  menuItem: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 15,
    paddingHorizontal: 20,
  },
  menuItemText: {
    fontSize: 16,
    marginLeft: 15,
    fontWeight: '500',
  },
  divider: {
    height: 1,
    backgroundColor: '#e0e0e0',
    marginVertical: 10,
  },
  logoutItem: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 15,
    paddingHorizontal: 20,
    marginTop: 20,
  },
  logoutText: {
    fontSize: 16,
    marginLeft: 15,
    fontWeight: '500',
  },
  footer: {
    paddingVertical: 15,
    paddingHorizontal: 20,
    borderTopWidth: 1,
    alignItems: 'center',
  },
  footerText: {
    fontSize: 12,
    marginBottom: 2,
  },
});

export default Sidebar;
