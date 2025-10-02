package com.widetech.funds_transfer.entityGraphPath;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.widetech.funds_transfer.entity.UserEntityGraph;

public enum UserEntityGraphPath {

  UserWithRoles {
    @Override
    public EntityGraph getEntityGraph() {
      return UserEntityGraph.____().roles().____.____();
    }
  },
  UserWithAccounts {
    @Override
    public EntityGraph getEntityGraph() {
      return UserEntityGraph.____().accounts().____.____();
    }
  },
  UserWithRolesAndAccounts {
    @Override
    public EntityGraph getEntityGraph() {
      return UserEntityGraph.____().roles().____.accounts().____.____();
    }
  };

  public abstract EntityGraph getEntityGraph();
}
