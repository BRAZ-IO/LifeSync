import React from 'react';
import { View, Text, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import { Card, Button, Avatar, List } from 'react-native-paper';
import { Ionicons } from '@expo/vector-icons';

const ProfileScreen: React.FC = () => {
  return (
    <ScrollView style={styles.container}>
      <View style={styles.header}>
        <Avatar.Image
          size={80}
          source={{ uri: 'https://via.placeholder.com/80' }}
          style={styles.avatar}
        />
        <Text style={styles.userName}>João Silva</Text>
        <Text style={styles.userEmail}>joao.silva@email.com</Text>
        <Text style={styles.userLevel}>Nível 12 • Atleta Intermediário</Text>
      </View>

      <Card style={styles.card}>
        <Card.Content>
          <Text style={styles.cardTitle}>Estatísticas Pessoais</Text>
          <View style={styles.statsGrid}>
            <View style={styles.statItem}>
              <Text style={styles.statNumber}>75kg</Text>
              <Text style={styles.statLabel}>Peso</Text>
            </View>
            <View style={styles.statItem}>
              <Text style={styles.statNumber}>1.75m</Text>
              <Text style={styles.statLabel}>Altura</Text>
            </View>
            <View style={styles.statItem}>
              <Text style={styles.statNumber}>24.5</Text>
              <Text style={styles.statLabel}>IMC</Text>
            </View>
            <View style={styles.statItem}>
              <Text style={styles.statNumber}>18%</Text>
              <Text style={styles.statLabel}>Gordura</Text>
            </View>
          </View>
        </Card.Content>
      </Card>

      <Card style={styles.card}>
        <Card.Content>
          <Text style={styles.cardTitle}>Metas e Objetivos</Text>
          <View style={styles.goalItem}>
            <Ionicons name="flag" size={20} color="#6200ee" />
            <View style={styles.goalContent}>
              <Text style={styles.goalTitle}>Perder 5kg</Text>
              <Text style={styles.goalProgress}>2kg perdidos • 40% completo</Text>
            </View>
          </View>
          <View style={styles.goalItem}>
            <Ionicons name="flag" size={20} color="#6200ee" />
            <View style={styles.goalContent}>
              <Text style={styles.goalTitle}>100 treinos</Text>
              <Text style={styles.goalProgress}>72 treinos • 72% completo</Text>
            </View>
          </View>
        </Card.Content>
      </Card>

      <Card style={styles.card}>
        <Card.Content>
          <Text style={styles.cardTitle}>Configurações</Text>
          <List.Item
            title="Editar Perfil"
            description="Atualizar informações pessoais"
            left={(props) => <List.Icon {...props} icon="account-edit" />}
            right={(props) => <List.Icon {...props} icon="chevron-right" />}
            onPress={() => console.log('Edit profile')}
          />
          <List.Item
            title="Notificações"
            description="Gerenciar alertas e lembretes"
            left={(props) => <List.Icon {...props} icon="bell" />}
            right={(props) => <List.Icon {...props} icon="chevron-right" />}
            onPress={() => console.log('Notifications')}
          />
          <List.Item
            title="Privacidade"
            description="Configurar privacidade de dados"
            left={(props) => <List.Icon {...props} icon="shield" />}
            right={(props) => <List.Icon {...props} icon="chevron-right" />}
            onPress={() => console.log('Privacy')}
          />
          <List.Item
            title="Assinatura"
            description="LifeSync Premium"
            left={(props) => <List.Icon {...props} icon="crown" />}
            right={(props) => <List.Icon {...props} icon="chevron-right" />}
            onPress={() => console.log('Subscription')}
          />
        </Card.Content>
      </Card>

      <Card style={styles.card}>
        <Card.Content>
          <Text style={styles.cardTitle}>Suporte</Text>
          <List.Item
            title="Central de Ajuda"
            description="FAQ e tutoriais"
            left={(props) => <List.Icon {...props} icon="help-circle" />}
            right={(props) => <List.Icon {...props} icon="chevron-right" />}
            onPress={() => console.log('Help center')}
          />
          <List.Item
            title="Contato"
            description="Fale conosco"
            left={(props) => <List.Icon {...props} icon="email" />}
            right={(props) => <List.Icon {...props} icon="chevron-right" />}
            onPress={() => console.log('Contact')}
          />
          <List.Item
            title="Sobre"
            description="Versão 1.0.0"
            left={(props) => <List.Icon {...props} icon="information" />}
            right={(props) => <List.Icon {...props} icon="chevron-right" />}
            onPress={() => console.log('About')}
          />
        </Card.Content>
      </Card>

      <View style={styles.logoutContainer}>
        <Button
          mode="outlined"
          onPress={() => console.log('Logout')}
          style={styles.logoutButton}
          textColor="#FF5252"
        >
          Sair da Conta
        </Button>
      </View>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5',
  },
  header: {
    backgroundColor: '#6200ee',
    padding: 30,
    alignItems: 'center',
  },
  avatar: {
    marginBottom: 15,
  },
  userName: {
    fontSize: 24,
    fontWeight: 'bold',
    color: 'white',
    marginBottom: 5,
  },
  userEmail: {
    fontSize: 16,
    color: 'rgba(255, 255, 255, 0.8)',
    marginBottom: 5,
  },
  userLevel: {
    fontSize: 14,
    color: 'rgba(255, 255, 255, 0.7)',
    backgroundColor: 'rgba(255, 255, 255, 0.2)',
    paddingHorizontal: 12,
    paddingVertical: 4,
    borderRadius: 12,
  },
  card: {
    margin: 15,
    marginHorizontal: 15,
  },
  cardTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 15,
  },
  statsGrid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
  },
  statItem: {
    width: '48%',
    alignItems: 'center',
    backgroundColor: '#f8f8f8',
    padding: 15,
    borderRadius: 8,
    marginBottom: 10,
  },
  statNumber: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#6200ee',
    marginBottom: 5,
  },
  statLabel: {
    fontSize: 12,
    color: '#666',
  },
  goalItem: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 15,
  },
  goalContent: {
    flex: 1,
    marginLeft: 15,
  },
  goalTitle: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 2,
  },
  goalProgress: {
    fontSize: 14,
    color: '#666',
  },
  logoutContainer: {
    padding: 20,
    paddingBottom: 40,
  },
  logoutButton: {
    borderColor: '#FF5252',
  },
});

export default ProfileScreen;
