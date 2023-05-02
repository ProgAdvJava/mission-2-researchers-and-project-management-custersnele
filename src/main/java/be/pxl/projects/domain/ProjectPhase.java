package be.pxl.projects.domain;

public enum ProjectPhase {
	INITIATING {
			@Override
			public ProjectPhase nextState() {
				return PLANNING;
			}
	},
	PLANNING {
		@Override
		public ProjectPhase nextState() {
			return EXECUTING;
		}
	},
	EXECUTING {
		@Override
		public ProjectPhase nextState() {
			return CLOSING;
		}
	},
	CLOSING {
		@Override
		public ProjectPhase nextState() {
			return this;
		}
	};

	public abstract ProjectPhase nextState();
}
