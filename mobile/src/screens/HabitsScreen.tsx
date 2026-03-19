import React, { useState } from 'react';
import { View, Text, StyleSheet, ScrollView, FlatList } from 'react-native';
import { Card, Checkbox, Button } from 'react-native-paper';
import { Ionicons } from '@expo/vector-icons';

interface Habit {
  id: string;
  name: string;
  description: string;
  completed: boolean;
  streak: number;
  category: string;
}

const habits: Habit[] = [
  {
    id: '1',
    name: 'Beber 2L de água',
    description: 'Manter-se hidratado durante o dia',
    completed: true,
    streak: 7,
    category: 'Hidratação',
  },
  {
    id: '2',
    name: 'Meditação matinal',
    description: '10 minutos de meditação',
    completed: false,
    streak: 3,
    category: 'Mental',
  },
  {
    id: '3',
    name: 'Proteína no pós-treino',
    description: 'Consumir shake de proteína',
    completed: true,
    streak: 14,
    category: 'Nutrição',
  },
  {
    id: '4',
    name: '8 horas de sono',
    description: 'Dormir bem para recuperação',
    completed: false,
    streak: 2,
    category: 'Descanso',
  },
  {
    id: '5',
    name: 'Alongamento',
    description: '15 minutos de alongamento',
    completed: false,
    streak: 5,
    category: 'Flexibilidade',
  },
];

const HabitsScreen: React.FC = () => {
  const [habitsList, setHabitsList] = useState(habits);

  const toggleHabit = (id: string) => {
    setHabitsList(prev =>
      prev.map(habit =>
        habit.id === id ? { ...habit, completed: !habit.completed } : habit
      )
    );
  };

  const renderHabit = ({ item }: { item: Habit }) => (
    <Card style={styles.card}>
      <Card.Content>
        <View style={styles.habitHeader}>
          <View style={styles.habitInfo}>
            <Text style={styles.habitName}>{item.name}</Text>
            <Text style={styles.habitDescription}>{item.description}</Text>
          </View>
          <Checkbox
            status={item.completed ? 'checked' : 'unchecked'}
            onPress={() => toggleHabit(item.id)}
          />
        </View>
        
        <View style={styles.habitFooter}>
          <View style={styles.categoryContainer}>
            <Ionicons name="pricetag" size={14} color="#6200ee" />
            <Text style={styles.categoryText}>{item.category}</Text>
          </View>
          
          <View style={styles.streakContainer}>
            <Ionicons name="flame" size={16} color="#FF6B35" />
            <Text style={styles.streakText}>{item.streak} dias</Text>
          </View>
        </View>
      </Card.Content>
    </Card>
  );

  const completedCount = habitsList.filter(h => h.completed).length;
  const completionPercentage = (completedCount / habitsList.length) * 100;

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.title}>Hábitos</Text>
        <Button mode="outlined" compact>
          + Novo
        </Button>
      </View>
      
      <View style={styles.progressContainer}>
        <Text style={styles.progressTitle}>Progresso de Hoje</Text>
        <Text style={styles.progressText}>
          {completedCount} de {habitsList.length} completos
        </Text>
        <View style={styles.progressBar}>
          <View
            style={[
              styles.progressFill,
              { width: `${completionPercentage}%` }
            ]}
          />
        </View>
      </View>
      
      <ScrollView style={styles.content}>
        <FlatList
          data={habitsList}
          renderItem={renderHabit}
          keyExtractor={(item) => item.id}
          scrollEnabled={false}
        />
      </ScrollView>
      
      <View style={styles.summaryContainer}>
        <Card style={styles.summaryCard}>
          <Card.Content>
            <Text style={styles.summaryTitle}>Resumo da Semana</Text>
            <View style={styles.summaryStats}>
              <View style={styles.statItem}>
                <Text style={styles.statNumber}>85%</Text>
                <Text style={styles.statLabel}>Taxa de conclusão</Text>
              </View>
              <View style={styles.statItem}>
                <Text style={styles.statNumber}>12</Text>
                <Text style={styles.statLabel}>Hábitos ativos</Text>
              </View>
            </View>
          </Card.Content>
        </Card>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5',
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: 20,
    backgroundColor: 'white',
    borderBottomWidth: 1,
    borderBottomColor: '#e0e0e0',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#333',
  },
  progressContainer: {
    backgroundColor: 'white',
    padding: 20,
    borderBottomWidth: 1,
    borderBottomColor: '#e0e0e0',
  },
  progressTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 5,
  },
  progressText: {
    fontSize: 14,
    color: '#666',
    marginBottom: 10,
  },
  progressBar: {
    height: 8,
    backgroundColor: '#e0e0e0',
    borderRadius: 4,
  },
  progressFill: {
    height: '100%',
    backgroundColor: '#4CAF50',
    borderRadius: 4,
  },
  content: {
    flex: 1,
    padding: 15,
  },
  card: {
    marginBottom: 15,
  },
  habitHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'flex-start',
    marginBottom: 10,
  },
  habitInfo: {
    flex: 1,
    marginRight: 10,
  },
  habitName: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 5,
  },
  habitDescription: {
    fontSize: 14,
    color: '#666',
  },
  habitFooter: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  categoryContainer: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  categoryText: {
    marginLeft: 5,
    fontSize: 12,
    color: '#6200ee',
  },
  streakContainer: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  streakText: {
    marginLeft: 5,
    fontSize: 14,
    fontWeight: 'bold',
    color: '#FF6B35',
  },
  summaryContainer: {
    padding: 15,
  },
  summaryCard: {
    backgroundColor: '#6200ee',
  },
  summaryTitle: {
    fontSize: 16,
    fontWeight: 'bold',
    color: 'white',
    marginBottom: 15,
    textAlign: 'center',
  },
  summaryStats: {
    flexDirection: 'row',
    justifyContent: 'space-around',
  },
  statItem: {
    alignItems: 'center',
  },
  statNumber: {
    fontSize: 24,
    fontWeight: 'bold',
    color: 'white',
  },
  statLabel: {
    fontSize: 12,
    color: 'rgba(255, 255, 255, 0.8)',
    marginTop: 2,
  },
});

export default HabitsScreen;
